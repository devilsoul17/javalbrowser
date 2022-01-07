package com.santhosh.browser_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class bookmarks extends AppCompatActivity {

    MydbHandlerBookmark mydbHandlerBookmark = new MydbHandlerBookmark( this , null, null, 1 );
    WebView mywebview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        getSupportActionBar().setTitle("Bookmarks");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        final List<String > books = mydbHandlerBookmark.databaseToString();
        if (books.size()>0)
        {
            ArrayAdapter myadapter = new  ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,books);
            ListView mylist = (ListView) findViewById(R.id.listviewBookmarks);
            mylist.setAdapter(myadapter);

            mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String url = books.get(i);
                    Toast.makeText(bookmarks.this, "Item selected :  " +url + " deleted", Toast.LENGTH_SHORT).show();
                    mydbHandlerBookmark.deleteUrl(url);
                    finish();
                    return false;
                }
            });

            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String url = books.get(position);
                    Intent intent= new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("urls",url);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        }
    }
}