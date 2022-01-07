package com.santhosh.browser_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class BrowserSettings extends AppCompatActivity {
    LinearLayout update;
    LinearLayout enginee;
//    LinearLayout security;
//    LinearLayout feedback;
//    LinearLayout developer;
//    LinearLayout report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_settings);


        getSupportActionBar().setTitle("Settings");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        update = findViewById(R.id.update);
        enginee = findViewById(R.id.engine);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BrowserSettings.this, "Update not available...", Toast.LENGTH_SHORT).show();
            }
        });

        enginee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BrowserSettings.this, "You cannot change to another engine right now....", Toast.LENGTH_SHORT).show();
            }
        });

    }
}