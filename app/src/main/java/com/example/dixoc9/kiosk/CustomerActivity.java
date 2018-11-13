package com.example.dixoc9.kiosk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CustomerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    String fName;
    String lName;
    String tele;
    String email;
    String item;
    EditText fNinput;
    EditText lNinput;
    EditText tPinput;
    EditText eAinput;
    Spinner itemType;
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

        itemType = findViewById(R.id.itemType);
        ArrayAdapter<CharSequence> a = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemType.setAdapter(a);
        itemType.setOnItemSelectedListener(this);

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v2)
            {
                openReviewActivity();
            }
        });
    }

    public void openReviewActivity()
    {
        Intent intent2 = new Intent(this, ReviewActivity.class);
        fName = fNinput.getText().toString();
        lName = lNinput.getText().toString();
        tele = tPinput.getText().toString();
        email = eAinput.getText().toString();

        intent2.putExtra("first", fName);
        intent2.putExtra("last", lName);
        intent2.putExtra("tP", tele);
        intent2.putExtra("eM", email);
        intent2.putExtra("itm", item);

        startActivity(intent2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        item = "not specified";
    }
}

