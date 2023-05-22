package com.edix.grupo02_codigo_control_de_acceso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class showqr extends AppCompatActivity {
    private ImageView qrImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showqr);
        getSupportActionBar().hide();

        //recuperamos el texto de la lista
        Intent intent = getIntent();
        String txtQr = intent.getStringExtra("txtQr");
        visualizarQr(txtQr);

        ImageButton botonHome = findViewById(R.id.botonHome);
        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showqr.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSearch = findViewById(R.id.botonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showqr.this, ScannerQR.class);
                startActivity(intent);
            }
        });

        ImageButton botonProfile = findViewById(R.id.botonProfile);
        botonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showqr.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    public void visualizarQr (String text){


        qrImageView = findViewById(R.id.qrImageView);

        String qrData = text;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 900, 900);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            qrImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

}