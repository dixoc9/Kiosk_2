package com.example.dixoc9.kiosk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import java.sql.*;

public class ReviewActivity extends AppCompatActivity
{
    //private String rvw;
    TextView rvwInfo;
    //private String qrID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        rvwInfo = findViewById(R.id.custInfo);

        Button mSubmitButton = findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v3)
            {
                //makeQRcode();
                Toast success = Toast.makeText(ReviewActivity.this, R.string.successButton, Toast.LENGTH_LONG);
                success.setGravity(Gravity.CENTER,0,0);
                success.show();
            }
        });
    }

    /*public void makeQRcode()
    {
        CustomerActivity ca1 = new CustomerActivity();
        ca1.

        /*Intent intent3 = new Intent(this, ReviewActivity.class);
        startActivity(intent3);
    }*/
}
