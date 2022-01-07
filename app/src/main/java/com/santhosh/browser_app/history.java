package com.santhosh.browser_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class history extends AppCompatActivity {

    MydbHandler mydbHandler = new MydbHandler( this , null, null, 1 );
    WebView mywebview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setTitle("History");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        final List<String > sites = mydbHandler.databaseTOString();
        if (sites.size()>0)
        {
            ArrayAdapter myadapter = new  ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,sites);
            ListView mylist = (ListView) findViewById(R.id.listviewHistory);
            mylist.setAdapter(myadapter);

            mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String url = sites.get(i);
                    Toast.makeText(history.this, "Item selected :  " +url + "   deleted", Toast.LENGTH_SHORT).show();
                    mydbHandler.deleteUrl(url);
                    finish();
                    return false;
                }
            });


            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String url = sites.get(i);
                    Intent intent = new Intent(view.getContext(),MainActivity.class);
                    intent.putExtra("urls",url);
                    startActivity(intent);
                    finishAffinity();
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.rateus:
                Uri uri = Uri.parse("http://play.google.com/store/details?id=" + getApplicationContext().getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;

            case R.id.refresh_history:
                finish();
                startActivity(getIntent());
                break;

            case R.id.share_app:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out best Browser App at : http://play.google.com/store/apps/details?id=" + history.this.getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            }
        return true;
    }
}