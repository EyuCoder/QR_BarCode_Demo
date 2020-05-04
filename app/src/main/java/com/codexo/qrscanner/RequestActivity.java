package com.codexo.qrscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class RequestActivity extends AppCompatActivity {

    Button generate;
    ImageView qr, bar;
    EditText amount, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        init();
    }

    private void init() {
        generate = findViewById(R.id.generate);
        qr = findViewById(R.id.qr);
        bar = findViewById(R.id.bar);
        amount = findViewById(R.id.amount);
        description = findViewById(R.id.description);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCode();
            }
        });
    }

    private void generateCode() {
        try {
            getQr();
            getBar();
        }catch (Exception e){
            Log.d("Generating: ", e.getMessage());
        }
    }

    //our Writer
    MultiFormatWriter formatWriter = new MultiFormatWriter();

    private void getBar() throws WriterException{
        String qrCode = amount.getText().toString() +"~"+description.getText().toString();
        BitMatrix bitMatrix = formatWriter.encode(qrCode, BarcodeFormat.CODE_128, 300,100, null);
        BarcodeEncoder encoder = new BarcodeEncoder();
        Bitmap bitmap = encoder.createBitmap(bitMatrix);
        bar.setImageBitmap(bitmap);
    }

    private void getQr() throws WriterException {
        String qrCode = amount.getText().toString() +"~"+description.getText().toString();
        BitMatrix bitMatrix = formatWriter.encode(qrCode, BarcodeFormat.QR_CODE, 350,300);
        BarcodeEncoder encoder = new BarcodeEncoder();
        Bitmap bitmap = encoder.createBitmap(bitMatrix);

        qr.setImageBitmap(bitmap);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
