package with.dee2.cameraapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View

class ServiceActivity : AppCompatActivity() {

    lateinit var serviceIntent:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        serviceIntent=Intent(this,MyService::class.java)
    }

    fun serviceStart(view : View){
        serviceIntent.action =MyService.ACTION_CREATE
        startService(intent)
    }


    fun serviceStop(view :View) {
        //intent.action =MyService.ACTION_DELETE
        stopService(intent)
    }


     var  myService: MyService? =null
    var isService=false
    val connection =object:ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            //bind되면 실행
            isService=true
            val binder=p1 as MyService.MyBinder
            myService =binder.getService()
        }
        override fun onServiceDisconnected(p0: ComponentName?) {
            //예외발생할 경우 호출되고 unbind는 아님
                isService=false
        }



    }
//view:View 넣어주면 onClick 사용 가능.
    fun serviceBind(view :View){
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
    }
    fun serviceCommand() {
        myService?.create()
        myService?.delete()
    }

    fun serviceUnbind(view :View) {

    }
}