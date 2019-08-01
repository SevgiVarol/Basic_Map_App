package com.example.task;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Activity2 extends Activity {

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        Button cancel = (Button) findViewById(R.id.btn_cancel);
        Button save = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }}
