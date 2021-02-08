package with.dee2.translate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        retrofitTest()
    }

    fun retrofitTest() {
        var retrofit=Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api=retrofit.create(API::class.java)

        api.getsjson("4").enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData?>, response: Response<JsonData?>) {
                val result = response.body()
                if (result != null) {
                    Log.d("결과 나오기 : ",result.body.toString() + "   "+result.title.toString())
                }
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}