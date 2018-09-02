package com.example.paras.myvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class AadharDetailsActivity extends AppCompatActivity {
    TextView AadharDetails,name,District;
    CheckBox good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AadharDetails=findViewById(R.id.textView1);
        name=findViewById(R.id.textView2);
        District=findViewById(R.id.textView3);
        String Name=getIntent().getExtras().getString("AadharDetails");
        name.setText(Name);

        good=findViewById(R.id.checkBox);

        good.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Intent next=new Intent(AadharDetailsActivity.this,ChooseCandidateActivity.class);
                next.putExtra("Holders",toString());
                startActivity(next);
            }
        });

    }
}
