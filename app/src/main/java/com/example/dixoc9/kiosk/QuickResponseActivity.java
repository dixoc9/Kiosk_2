package com.example.dixoc9.kiosk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class QuickResponseActivity extends AppCompatActivity
{
    ImageView qrcImg;
    Bitmap bMap;
    Button mPrintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_response);

        qrcImg = findViewById(R.id.qrCodeView);
        bMap = getIntent().getParcelableExtra("bMp");
        qrcImg.setImageBitmap(bMap);

        mPrintButton = findViewById(R.id.printButton);
        mPrintButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v5)
            {
                Toast success = Toast.makeText(QuickResponseActivity.this, R.string.successButton, Toast.LENGTH_SHORT);
                success.setGravity(Gravity.CENTER, 0, 0);
                success.show();
                //printQRcode(bM);
                startNewSession();
            }
        });
    }

    /* METHOD TO PRINT THE QR CODE ON THE LABEL PRINTER
    public void printQRcode(Bitmap bMap)
    {
        //printing by name(qrid) perhaps to receipt paper or sticker paper
    }*/

    /* METHOD TO RETURN TO THE GET STARTED PAGE FOR THE NEXT CUSTOMER */
    public void startNewSession()
    {
        Intent intent5 = new Intent(this, MainActivity.class);
        startActivity(intent5);
    }
}


