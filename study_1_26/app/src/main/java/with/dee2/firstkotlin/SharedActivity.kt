package with.dee2.firstkotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SharedActivity : AppCompatActivity() {

    val SP_Name="my_sp_story"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared)

 //       writeSharedPreference("name","마이클")
        val value =readSharedPreference("name")
        Log.d("프리퍼런스","name=$value")
    }
    fun writeSharedPreference(key:String,value:String){
        val sp =getSharedPreferences(SP_Name, Context.MODE_PRIVATE)
        val editor =sp.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun readSharedPreference(key:String) :String{
        val sp=getSharedPreferences(SP_Name,Context.MODE_PRIVATE)
        return sp.getString("name","") ?:" "
    }
}