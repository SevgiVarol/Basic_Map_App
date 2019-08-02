package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
    lateinit var myDataBase: MyDataBase


    override fun onCreate(savedInstanceState: Bundle?) {

        myDataBase =
            Room.databaseBuilder(applicationContext, MyDataBase::class.java, "recorddb").allowMainThreadQueries()
                .build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav : NavigationView = findViewById(R.id.nav_view);
        val menu = nav.getMenu()
        val submenu = menu.addSubMenu("All events list")
        nav.setOnClickListener {
            Toast.makeText(applicationContext, "kayıt",Toast.LENGTH_LONG).show()
        }

        val productList = myDataBase.myDao().getAll()
        for (i in productList.indices) {
            submenu.add(productList.get(i).event);
            //Toast.makeText(applicationContext, "kayıt-"+ productList.get(i).event+"-"+productList.get(i).detail+"-"+productList.get(i).type, Toast.LENGTH_LONG).show()

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            mMap.setOnMapClickListener() {
                //allPoints.add(it)
                mMap.clear()
                mMap.addMarker(MarkerOptions().position(it))
                fab.setImageResource(R.drawable.tik_burned);
                fab.setOnClickListener {
                    val intent = Intent(this, Activity2::class.java)
                    finish();
                    startActivity(getIntent());
                    startActivity(intent);
                    }
                }
            }
        }



    }


