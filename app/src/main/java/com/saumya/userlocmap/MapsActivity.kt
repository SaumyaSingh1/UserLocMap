package com.saumya.userlocmap

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    private  var latlong :LatLng? =null

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val locationmanager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        val locationlistener = object : LocationListener {
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {


            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }

            override fun onLocationChanged(location: Location?) {

                if (location != null) {
                    location.latitude
                    location.longitude
                }
                if (location != null) {
                    latlong= LatLng(location.latitude , location.longitude)
                }
                Log.e("TAG", location?.latitude.toString())

                Log.e("Longtitude", location?.latitude.toString())

                Toast.makeText(applicationContext, location?.latitude.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.addMarker(latlong?.let { MarkerOptions().position(it).title("Marker at current loc") })
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latlong))
    }
}
