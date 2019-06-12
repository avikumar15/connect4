package com.example.deltahacker2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TypeActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER3 = "khanakhakejana";
    public static final String EXTRA_NUMBER4 = "bhuklagihai";

    float x,y;

    Button single,couple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        single=(Button)findViewById(R.id.single);
        Intent intent = getIntent();
        x = intent.getFloatExtra(StartActivity.EXTRA_NUMBER1,7);
        y = intent.getFloatExtra(StartActivity.EXTRA_NUMBER2,6);
        couple=(Button)findViewById(R.id.couple);
    }

    public void single(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra(EXTRA_NUMBER3,x);
        i.putExtra(EXTRA_NUMBER4,y);
        startActivity(i);
    }

    public void couple(View v1)
    {
        Intent j = new Intent(this,DoublePlayer.class);
        j.putExtra(EXTRA_NUMBER3,x);
        j.putExtra(EXTRA_NUMBER4,y);
        startActivity(j);
    }
}
