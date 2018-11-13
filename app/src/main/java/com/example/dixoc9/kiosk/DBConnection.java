package com.example.dixoc9.kiosk;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class DBConnection extends AsyncTask<String, Void, String>
{
    Context context;
    AlertDialog ad;

    DBConnection(Context c)
    {
        context = c;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String type = params[0];
        String update_url = "http://student2.cs.appstate.edu/dixoncs/SecureShip/skeleton/updateDatabase.php";
        if(type.equals("update"))
        {
            try
            {
                String first = params[1];
                String last = params[2];
                String phone = params[3];
                String emAddr = params[4];
                String item = params[5];
                String qrid = params[6];
                String cost = params[7];
                String date = params[8];
                String time = params[9];
                String airport = params[10];
                String kiosk = params[11];
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String postData = URLEncoder.encode("first", "UTF-8")+"="+URLEncoder.encode(first, "UTF-8")+"&"
                        +URLEncoder.encode("last", "UTF-8")+"="+URLEncoder.encode(last, "UTF-8")+"&"
                        +URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(phone, "UTF-8")+"&"
                        +URLEncoder.encode("emAddr", "UTF-8")+"="+URLEncoder.encode(emAddr, "UTF-8")+"&"
                        +URLEncoder.encode("item", "UTF-8")+"="+URLEncoder.encode(item, "UTF-8")+"&"
                        +URLEncoder.encode("qrid", "UTF-8")+"="+URLEncoder.encode(qrid, "UTF-8")+"&"
                        +URLEncoder.encode("cost", "UTF-8")+"="+URLEncoder.encode(cost, "UTF-8")+"&"
                        +URLEncoder.encode("date", "UTF-8")+"="+URLEncoder.encode(date, "UTF-8")+"&"
                        +URLEncoder.encode("time", "UTF-8")+"="+URLEncoder.encode(time, "UTF-8")+"&"
                        +URLEncoder.encode("airport", "UTF-8")+"="+URLEncoder.encode(airport, "UTF-8")+"&"
                        +URLEncoder.encode("kiosk", "UTF-8")+"="+URLEncoder.encode(kiosk, "UTF-8");
                bw.write(postData);
                bw.flush();
                bw.close();
                os.close();
                InputStream ipst = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ipst, "iso-8859-1"));
                String result = "";
                String line;
                while((line = br.readLine()) != null)
                {
                    result += line;
                }
                br.close();
                ipst.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        //super.onPreExecute();
        ad = new AlertDialog.Builder(context).create();
        ad.setTitle("Update Status");
    }

    @Override
    protected void onPostExecute(String s)
    {
        //super.onPostExecute(s);
        ad.setMessage(s);
        ad.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}

