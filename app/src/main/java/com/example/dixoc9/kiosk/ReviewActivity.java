package com.example.dixoc9.kiosk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ReviewActivity extends AppCompatActivity
{
    TextView rvwInfo;
    Bitmap bM;
    Button mSubmitButton;
    Button mBackButton;
    String fName;
    String lName;
    String tele;
    String email;
    String item;
    String cost;
    String arptID;
    String kNum;
    Date toDay;
    String dS;
    String tS;
    String allInfo;
    String qRiD;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        rvwInfo = findViewById(R.id.custInfo);
        fName = getIntent().getExtras().getString("first");
        lName = getIntent().getExtras().getString("last");
        tele = getIntent().getExtras().getString("tP");
        email = getIntent().getExtras().getString("eM");
        item = getIntent().getExtras().getString("itm");
        cost = getPrice(item);
        arptID = "DEN";
        kNum = "01";
        toDay = new Date();
        dS = getCurrDate(toDay);
        tS = getCurrTime(toDay);
        allInfo = fName + " " + lName + "\n" + tele + "\n" + email + "\n" + item + "\n" + "$" + cost;
        rvwInfo.setText(allInfo);
        makeQRid();
        String t = fName.toUpperCase() + lName.toUpperCase() + tele + email.toUpperCase()
                + item.toUpperCase() + cost + tS + dS + "DEN" + "01";
        makeQRcode(t);

        mSubmitButton = findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v3)
            {
                updateDatabase(fName, lName, tele, email, item, qRiD, cost, dS, tS, arptID, kNum);
                openQuickResponseActivity();
            }
        });

        mBackButton = findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v4)
            {
                backToCustomerActivity();
            }
        });
    }

    /* METHOD TO GO TO THE QR CODE SCREEN WHEN SUBMIT BUTTON IS PRESSED */
    public void openQuickResponseActivity()
    {
        Intent intent3 = new Intent(this, QuickResponseActivity.class);
        intent3.putExtra("bMp", bM);
        startActivity(intent3);
    }

    /* METHOD TO GO BACK TO CUSTOMER INPUT SCREEN WHEN BACK BUTTON IS PRESSED */
    public void backToCustomerActivity()
    {
        Intent intent4 = new Intent(this, CustomerActivity.class);
        startActivity(intent4);
    }

    /* METHOD TO GET THE CURRENT TIME IN THE REQUIRED FORMAT */
    public String getCurrTime(Date t)
    {
        SimpleDateFormat rNow = new SimpleDateFormat("HHmmss");
        return rNow.format(t);
    }

    /* METHOD TO GET THE CURRENT DATE IN THE REQUIRED FORMAT */
    public String getCurrDate(Date d)
    {
        SimpleDateFormat tDay = new SimpleDateFormat("MMddyy");
        return tDay.format(d);
    }

    /* METHOD TO GENERATE THE QR_ID FOR THE CUSTOMER TO BE USED IN THE DATABASE */
    public void makeQRid()
    {
        String lFive = makeFIVE(lName);
        String fFive = makeFIVE(fName);
        String dateStamp = dS;
        String timeStamp = tS;
        String airport = arptID;
        String kiosk = kNum;
        qRiD = lFive + fFive + dateStamp + timeStamp + airport + kiosk;
    }

    /* METHOD THAT TAKES IN A NAME AND MAKES IT FIVE CHARACTERS LONG FOR THE QR_ID IN THE DATABASE */
    public String makeFIVE(String s)
    {
        String piece = "";
        if (s.length() >= 5)
        {
            for (int i = 0; i < 5; i++)
            {
                piece += s.charAt(i);
            }
        }
        else
        {
            int shortage = 5 - s.length();
            for (int i = 0; i < s.length(); i++)
            {
                piece += s.charAt(i);
            }
            for (int j = 0; j < shortage; j++)
            {
                piece += "-";
            }
        }
        piece = piece.toUpperCase();
        return piece;
    }

    /* METHOD TO DETERMINE THE PRICE OF THE ITEM */
    public String getPrice(String i)
    {
        String price;
        switch (i)
        {
            case "Knife/Tool ($17.95)":
                price = "17.95";
                break;
            case "Liquid ($25.95)":
                price = "25.95";
                break;
            case "Lighter ($27.95)":
                price = "27.95";
                break;
            case "Other ($18.95)":
                price = "18.95";
                break;
            case "not specified":
                price = "REQUEST";
                break;
            default:
                price = "DEFAULT";
                break;
        }
        return price;
    }

    /* METHOD TO GENERATE A QR CODE USING CUSTOMER INPUT AND WHATNOT */
    public void makeQRcode(String n)
    {
        if(n != null)
        {
            MultiFormatWriter mFW = new MultiFormatWriter();
            try
            {
                BitMatrix qrBM = mFW.encode(n, BarcodeFormat.QR_CODE, 250, 250);
                BarcodeEncoder bcEcdr = new BarcodeEncoder();
                bM = bcEcdr.createBitmap(qrBM);
            }
            catch (WriterException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /* METHOD TO UPDATE THE DATABASE WITH ALL NECESSARY INFORMATION */
    public void updateDatabase(String f, String l, String p, String e, String i, String q, String c, String d, String t, String a, String k)
    {
        String type = "update";
        DBConnection dbc = new DBConnection(this);
        dbc.execute(type, f, l, p, e, i, q, c, d, t, a, k);
    }
}


