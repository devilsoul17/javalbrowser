package com.santhosh.browser_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ScanQrCode extends AppCompatActivity //implements View.OnClickListener
 {
    ImageButton scanQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        getSupportActionBar().setTitle("Qr Scanner");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        scanQr = findViewById(R.id.btnScanQrCode);
         scanQr.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(ScanQrCode.this, "Qr-Code Scanner will be Available soon", Toast.LENGTH_SHORT).show();
             }
         });
//        initViews();
    }

//    private void initViews() {
//        btnScanQrcode = findViewById(R.id.btnScanQrCode);
//        btnScanQrcode.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btnScanQrCode:
//                startActivity(new Intent(ScanQrCode.this,ScanQrCode.class));
//                break;
//        }
//    }
}