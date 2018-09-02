package com.example.paras.myvote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText aadharNumber;
    Button submit;
    public static final String MY_PREFERENCE = "myPref";


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aadharNumber = findViewById(R.id.editText);
        submit = findViewById(R.id.button2);

        SharedPreferences editor = getSharedPreferences(MY_PREFERENCE, MODE_PRIVATE);
        String restoredText = editor.getString("aadharNumber", "");

        aadharNumber.setText(restoredText);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Aadhar = aadharNumber.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFERENCE, MODE_PRIVATE).edit();
                editor.putString("aadharNumber", Aadhar);
                editor.commit();


                if (!Aadhar.equalsIgnoreCase("")) {
                    Toast.makeText(LoginActivity.this, "Welcome " + Aadhar + " !!!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(LoginActivity.this, AadharDetailsActivity.class);
                    i.putExtra("AadharDetails", Aadhar.toString());
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Enter Aadhar Number", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
