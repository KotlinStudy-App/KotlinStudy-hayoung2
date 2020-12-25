package with.dee2.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.*

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val dir=filesDir.absolutePath
        val filename="first.txt"
        val contents="파이 내용쓰기 안녕하세요 첫번ㅉ ㅐ 줄 입니다";

        //writeTextFile(dir,filename,contents)

        val result=readTextFile(dir+"/"+filename)
        Log.d("파일내용",result)
    }
    fun readTextFile(fullpath:String):String {
        val file =File(fullpath)
        if (!file.exists()){
            return ""
        }

        val reader =FileReader(file)
        val buffer = BufferedReader(reader)

        var temp:String? =""
        var result =StringBuffer()

        while(true) {
            temp =buffer.readLine()
            if (temp ==null) break
            else result.append(temp).append("\n")
        }

        buffer.close()
        return result.toString()
    }

    fun writeTextFile(directory:String , filename:String, conente:String){
      val dir= File(directory)

        if (!dir.exists()){
            dir.mkdirs() //중간에 디렉토리 없어도 실행
        }
        val fullpath="$directory/$filename"
        val writer =FileWriter(fullpath)
        val buffer =BufferedWriter(writer)

        buffer.write(conente)
        buffer.close()


    }
}