package com.example.deltahacker2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER1 = "letgo";
    public static final String EXTRA_NUMBER2 = "nanga";

    EditText width,height;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        width=(EditText)findViewById(R.id.width);
        height=(EditText)findViewById(R.id.height);

        go=(Button)findViewById(R.id.goButton);
    }

    public void GO (View view)
    {
        float i,j;

        i=Float.parseFloat(width.getText().toString());
        j=Float.parseFloat(height.getText().toString());

        Intent intent = new Intent(this,TypeActivity.class);
        intent.putExtra(EXTRA_NUMBER1,i);
        intent.putExtra(EXTRA_NUMBER2,j);
        startActivity(intent);
    }

}
