package with.dee2.translate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.MediaType


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val JSON = MediaType.parse("application/json;")
    }
}