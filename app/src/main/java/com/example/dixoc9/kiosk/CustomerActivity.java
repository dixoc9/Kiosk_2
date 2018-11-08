package com.example.dixoc9.kiosk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;

public class CustomerActivity extends AppCompatActivity
{
    private String fName;
    private String lName;
    private String email;
    private String tele;

    EditText fNinput;
    EditText lNinput;
    EditText tPinput;
    EditText eAinput;

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        fNinput = findViewById(R.id.firstName);
        lNinput = findViewById(R.id.lastName);
        tPinput = findViewById(R.id.telephone);
        eAinput = findViewById(R.id.email);

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v2)
            {
                fName = fNinput.getText().toString();
                lName = lNinput.getText().toString();
                email = eAinput.getText().toString();
                tele = tPinput.getText().toString();

                openReviewActivity();
            }
        });
    }

    public void openReviewActivity()
    {
        Intent intent2 = new Intent(this, ReviewActivity.class);
        startActivity(intent2);
    }
}

