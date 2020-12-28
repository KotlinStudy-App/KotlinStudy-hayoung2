package with.dee2.cameraapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_async_test.*
import java.lang.Exception
import java.net.URL

class AsyncTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_test)

        buttonDownload.setOnClickListener {
            downloadAndSetImage()
        }
    }

    fun downloadAndSetImage() {
        var url=editUrl.text.toString()
        if(url.startsWith("http")){
            url="http://$url"
        }
        //interface 추상이라 코드 구현해야함
        //background 처리 후 main therad에 화면 처리
        //handler로 아래 함수 호출
        val asyncTask=object : AsyncTask<String,Void, Bitmap?>(){
            override fun doInBackground(vararg p0: String?): Bitmap? {

                val urlString=p0[0]?:""
                var bitmap:Bitmap? =null
                try {
                    val url = URL(url)
                    //주소에 파이프 연결이라고 생각
                    val stream = url.openStream()
                    bitmap = BitmapFactory.decodeStream(stream)
                }catch (e:Exception){
                    Log.e("errpr", "${e.localizedMessage}")
                }
                return bitmap
            }

//            //메인쓰레드 /포어그라운드 영역
//            override fun onProgressUpdate(vararg values: Void?) {
//                super.onProgressUpdate(*values)
//            }

            override fun onPostExecute(result: Bitmap?) {
                result?.let {
                    imagePreview.setImageBitmap(it)
                }
            }

        }
        asyncTask.execute(url)

    }
}