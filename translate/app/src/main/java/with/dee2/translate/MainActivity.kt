package with.dee2.translate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        papagoAPI()

        button.setOnClickListener {
            val intent= Intent(this,SubActivity::class.java)
            startActivity(intent)
        }

    }


    fun papagoAPI() {
        val JSON = MediaType.parse("application/json; charset=utf-8")

        val client =OkHttpClient()
        var url ="https://openapi.naver.com/v1/papago/n2mt"
        var json =JSONObject()
        json.put("source","ko")
        json.put("target","en")
        json.put("text","하이 나는 하영")
        val body=RequestBody.create(JSON,json.toString())
        val request = Request.Builder().header("X-Naver-Client-Id","")
            .addHeader("X-Naver-Client-Secret","")
            .url(url).post(body).build()

        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

                var result=Gson().fromJson<Papage>(response.body()?.string(),Papage::class.java)
                Log.d("번역 : ",result.message.result.translatedText!!)
            }

        })
    }
}






