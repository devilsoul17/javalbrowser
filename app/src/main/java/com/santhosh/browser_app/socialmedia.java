package com.santhosh.browser_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class socialmedia extends AppCompatActivity {

    ImageButton instag;
    ImageButton twit;
    ImageButton facebook;
    ImageButton youtube;
    ImageButton skype;
    ImageButton pintrest;
    ImageButton linkedin;
//    ImageButton blogspot;
//    ImageButton messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialmedia);

        getSupportActionBar().setTitle("Social Media");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        instag = findViewById(R.id.insta1);
        twit = findViewById(R.id.twiter1);
        facebook = findViewById(R.id.facebook1);
        youtube = findViewById(R.id.youtube1);
        skype = findViewById(R.id.skype1);
        pintrest = findViewById(R.id.pintrest1);
        linkedin = findViewById(R.id.linkedin1);
//        blogspot = findViewById(R.id.blog1);
//        messenger = findViewById(R.id.messenger1);

        instag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/");
            }
        });

        twit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://twitter.com/i/flow/login?input_flow_data=%7B%22requested_variant%22%3A%22eyJsYW5nIjoiZW4ifQ%3D%3D%22%7D");
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.facebook.com/?stype=lo&jlou=AfcuHbmpqLhXqVBtle81lOIQdqEpEtlGW-J8764IAFHi05QNdJMRiHECaaI7CipS-ymcMY4-uM6jPG3y66cvVmfFe5vTFSiqzjPMDJMgl0LWOQ&smuh=40692&lh=Ac__JBKhhx31ffp--YA");
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/");
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.linkedin.com/signup");
            }
        });

        pintrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://in.pinterest.com/");
            }
        });

        skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.skype.com/en/");
            }
        });

//        blogspot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotoUrl("https://devilsoul17.blogspot.com/");
//            }
//        });
//
//        messenger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotoUrl("https://www.messenger.com/");
//            }
//        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}