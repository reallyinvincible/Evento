package com.ieeevit.evento.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ieeevit.evento.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static maes.tech.intentanim.CustomIntent.customType;

public class ZoomedImageActivity extends AppCompatActivity {

    private final String QR_BASE_URL = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed_image);
        Intent intent = getIntent();
        String qrString = intent.getStringExtra("QrData");

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix matrix = multiFormatWriter.encode(qrString, BarcodeFormat.QR_CODE, 600,600);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(matrix);
            Glide.with(this)
                    .load(bitmap)
                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                    .into((ImageView) findViewById(R.id.qrImageView));

        } catch (WriterException e) {
            e.printStackTrace();
        }

        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZoomedImageActivity.this, HomeActivity.class);
                startActivity(intent);
                customType(ZoomedImageActivity.this, "up-to-bottom");
                finishAfterTransition();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ZoomedImageActivity.this, HomeActivity.class);
        startActivity(intent);
        customType(ZoomedImageActivity.this, "up-to-bottom");
        finishAfterTransition();
    }
}
