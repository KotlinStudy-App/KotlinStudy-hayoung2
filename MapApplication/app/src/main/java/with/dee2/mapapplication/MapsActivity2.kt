package with.dee2.mapapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import with.dee2.mapapplication.data.Library

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        loadLibraries()
    }

    fun loadLibraries() {
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SeoulOpenService::class.java)

        service.getLibraries(SeoulOpenApi.API_KEY,200)
            .enqueue(object : Callback<Library> {
                override fun onFailure(call: Call<Library>, t: Throwable) {
                    Toast.makeText(this@MapsActivity2,"실패함",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    val result= response.body()
                    showLibraries(result)
                }

            })
    }

    fun showLibraries(result:Library?){

        result?.let {
            val latlngBounds=LatLngBounds.Builder()
            for(library in it.SeoulPublicLibraryInfo.row){
                val position= LatLng(library.XCNTS.toDouble(),library.YDNTS.toDouble())
                val marker= MarkerOptions().position(position).title(library.LBRRY_NAME)

                mMap.addMarker(marker)
                latlngBounds.include(position)
            }
            val bounds =latlngBounds.build()
            val padding=0

            val camera=CameraUpdateFactory.newLatLngBounds(bounds,padding)
            mMap.moveCamera(camera)
        }


    }
}