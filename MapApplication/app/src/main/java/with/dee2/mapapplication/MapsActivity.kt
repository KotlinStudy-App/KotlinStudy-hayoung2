package with.dee2.mapapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    val permission=arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
    val PERM_FLAG=99
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(isPermitted()){
            startProcess()
        }else{
            ActivityCompat.requestPermissions(this,permission,PERM_FLAG)
        }
    }




    fun isPermitted():Boolean {

        for(p in permission){
            if(ContextCompat.checkSelfPermission(this,p) !=PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    fun startProcess() {
        val mapFragment =supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }




    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
        setUpdateLocationListener()
    }


    lateinit var fusedLocationClient: FusedLocationProviderClient
   lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    fun setUpdateLocationListener(){
        val locationReqest =LocationRequest.create()
        locationReqest.run{
            priority=LocationRequest.PRIORITY_HIGH_ACCURACY
            interval =1000
        }

        locationCallback=object: LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                p0?.let {
                    for((i,location)in it.locations.withIndex()){
                        Log.d("로케이션","$i ${location.latitude}, ${location.longitude}")
                        setLastLocation(location)
                    }
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(locationReqest,locationCallback, Looper.myLooper())
    }

    fun setLastLocation(location:Location){
        val myLocation =LatLng(location.latitude,location.longitude)
        val descriptor= getDescriptorFromDrawable(R.drawable.marker)

        val marker =MarkerOptions()
            .position(myLocation)
            .title("I am here")
            .icon(descriptor)

        val cameraOption =CameraPosition.Builder().target(myLocation)
            .zoom(15.0f).build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)

        mMap.clear()
        mMap.addMarker(marker)
        mMap.moveCamera(camera)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERM_FLAG->{
                var check =true
                for( grant in grantResults){
                    if(grant!= PERMISSION_GRANTED){
                        check=false
                        break
                    }
                }
                if(check){
                    startProcess()
                }else{
                    Toast.makeText(this,"권한 승인해야함 아니면 사용 ㄴ",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getDescriptorFromDrawable(drawableId:Int) :BitmapDescriptor{
        var bitmapDrawable: BitmapDrawable
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            bitmapDrawable=getDrawable(R.drawable.marker) as BitmapDrawable
        }else{
            bitmapDrawable=resources.getDrawable(R.drawable.marker) as BitmapDrawable
        }
        val scaledBitmap = Bitmap.createScaledBitmap(bitmapDrawable.bitmap,100,100,false )


        return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    }

}