package com.example.paras.myvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {
    TextView tv1,display,tv2,tv3;
    Button no,vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        tv1=findViewById(R.id.textView5);
        tv2=findViewById(R.id.textView7);
        tv3=findViewById(R.id.textView8);
        display=findViewById(R.id.textView6);

        String Display=getIntent().getExtras().getString("selectedCandidate");
        display.setText(Display);

        no=findViewById(R.id.button2);
        vote=findViewById(R.id.button3);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(ConfirmationActivity.this,ChooseCandidateActivity.class);
                startActivity(k);
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l= new Intent(ConfirmationActivity.this,ThanksActivity.class);
                startActivity(l);
            }
        });
    }
}
