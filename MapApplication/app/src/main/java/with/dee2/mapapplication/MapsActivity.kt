package with.dee2.mapapplication

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val seoul =LatLng(37.5663,126.9779)
  val descriptor= getDescriptorFromDrawable(R.drawable.marker)

        //val descriptor=BitmapDescriptorFactory.fromBitmap(scaledBitmap)
        // Add a marker in Sydney and move the camera
       // val sydney = LatLng(-34.0, 151.0)
       // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        val marker=MarkerOptions().position(seoul).title("Seoul").icon(descriptor)
        mMap.addMarker(marker)

        val cameraOption =CameraPosition.Builder().target(seoul).zoom(17f).build()
        val camera=CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)
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