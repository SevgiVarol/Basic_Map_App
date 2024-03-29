package com.example.task;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.room.Insert;
import androidx.room.Room;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class Activity2 extends Activity {
    private static final String TAG = Activity2.class.getName();

    public static MyDataBase myDataBase;
    private EditText event,detail;
    private Spinner type;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        myDataBase = Room.databaseBuilder(getApplicationContext(),MyDataBase.class,"recorddb").allowMainThreadQueries().build();
        final LatLng location = getIntent().getExtras().getParcelable("location");
        final double lat =location.latitude;
        final double lng = location.longitude;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        Button cancel = (Button) findViewById(R.id.btn_cancel);
        Button save = (Button) findViewById(R.id.btn_save);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                event= (EditText) findViewById(R.id.edit_event);
                detail= (EditText) findViewById(R.id.edit_detail);
                type= (Spinner) findViewById(R.id.spin_type);
                String event_st= event.getText().toString();
                String detail_st= detail.getText().toString();
                String type_st= type.getSelectedItem().toString();

                Product product = new Product();
                product.setEvent(event_st);
                product.setDetail(detail_st);
                product.setType(type_st);
                product.setLat(lat);
                product.setLng(lng);

                Log.d(Activity2.TAG, "1: " + product.getEvent());
                Log.d(Activity2.TAG, "2: " + product.getDetail());
                Log.d(Activity2.TAG, "3: " + product.getType());
                Log.d(Activity2.TAG, "4: " + product.getLat()+","+product.getLng());
                myDataBase.myDao().addRecord(product);

                Toast.makeText(getApplicationContext(),"kayit eklendi",Toast.LENGTH_LONG).show();

                List<Product> productList = myDataBase.myDao().getAll();
                Log.d(Activity2.TAG, "Rows Count: " + myDataBase.myDao().countUsers());
                for (int i = 0; i < productList.size(); i++) {
                    Log.d(Activity2.TAG, "Rows Name: " + productList.get(i).getId()+"-"+productList.get(i).getEvent()+"-"+productList.get(i).getDetail()+"-"+productList.get(i).getType()+"-"+productList.get(i).getLat()+","+productList.get(i).getLng());
                }

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }}
