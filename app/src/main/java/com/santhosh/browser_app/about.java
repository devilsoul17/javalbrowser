package com.santhosh.browser_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class about extends AppCompatActivity {

    ImageButton tweet;
    ImageButton insta;
    ImageButton facebooks;
    ImageButton blogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle("About");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        tweet = findViewById(R.id.twitter);
        insta = findViewById(R.id.instagram);
        facebooks = findViewById(R.id.facebk);
        blogger = findViewById(R.id.blogr);

        tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://twitter.com/Sandy_17_?t=FCQ8_DC5Z8Iqb3L-cWMLmw&s=09");
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/candy_.king_.17/");
            }
        });

        facebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.facebook.com/sandy.santhosh.50309277/");
            }
        });

        blogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://devilsoul17.blogspot.com/");
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}