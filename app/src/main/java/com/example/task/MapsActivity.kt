package com.example.task

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.widget.PopupWindow
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.Marker


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener,
    GoogleMap.OnInfoWindowClickListener {
    private lateinit var mMap: GoogleMap
    lateinit var myDataBase: MyDataBase
    lateinit var text: String

    override fun onInfoWindowClick(p0: Marker?) {
        val dialogs = Dialog(this)
        dialogs.setContentView(R.layout.popupview);
        val txt : TextView = dialogs.findViewById(R.id.textView2);
        txt.setText(text)
        dialogs.show()
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val eventname =p0.toString()

        myDataBase =
            Room.databaseBuilder(applicationContext, MyDataBase::class.java, "recorddb").allowMainThreadQueries()
                .build()

        val productList = myDataBase.myDao().getEventDetails(eventname)
        val location =LatLng(productList.get(0).lat,productList.get(0).lng)
        text = "event->" + productList.get(0).event + "\n"+
                "details->"+ productList.get(0).detail+"\n"+"type->"+productList.get(0).type
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(location).title("Info")).showInfoWindow()
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.setOnInfoWindowClickListener(this);

        val mDrawerLayout : DrawerLayout= findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();

        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        myDataBase =
            Room.databaseBuilder(applicationContext, MyDataBase::class.java, "recorddb").allowMainThreadQueries()
                .build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav : NavigationView = findViewById(R.id.nav_view);
        val menu = nav.getMenu()
        val submenu = menu.addSubMenu("All events list")
        if (nav != null) nav.setNavigationItemSelectedListener(this);

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

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            mMap.setOnMapClickListener() {
                //allPoints.add(it)
                mMap.clear()
                mMap.addMarker(MarkerOptions().position(it))
                val loc = it

                fab.setImageResource(R.drawable.tik_burned);
                fab.setOnClickListener {

                    val intent = Intent(this, Activity2::class.java)
                    intent.putExtra("location",loc)
                    finish();
                    startActivity(getIntent());
                    startActivity(intent);
                    }
                }
            }
        }



    }


