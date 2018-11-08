package com.example.dixoc9.kiosk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mStartButton = findViewById(R.id.getStarted);
        mStartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v1)
            {
                openCustomerActivity();
            }
        });
    }

    public void openCustomerActivity()
    {
        Intent intent1 = new Intent(this, CustomerActivity.class);
        startActivity(intent1);
    }
}
