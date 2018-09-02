package com.example.paras.myvote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ThanksActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tv1,tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        imageView=findViewById(R.id.imageView);
        tv1=findViewById(R.id.textView9);
        tv2=findViewById(R.id.textView10);
    }
}
