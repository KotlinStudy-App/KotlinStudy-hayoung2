package with.dee2.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest

class MainActivity : AppCompatActivity() {

    val permissions=arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}