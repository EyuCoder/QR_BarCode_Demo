package com.codexo.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button send, scan;
    ImageView qr, bar;
    Intent intent, intent2;
    public TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        send = findViewById(R.id.request);
        scan = findViewById(R.id.scan_pay);
        price = (TextView) findViewById(R.id.msg);
        intent = new Intent(this, RequestActivity.class);
        intent2 = new Intent(this, ScannerActivity.class);

        send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

    }
}
