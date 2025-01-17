package with.dee2.cameraapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {


    companion object {
        val ACTION_CREATE = "create"
        val ACTION_DELETE = "delete"

    }

    inner class MyBinder : Binder() {
        fun getService():MyService{
            return this@MyService
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return MyBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action =intent?.action
        when(action) {
            ACTION_CREATE->create()
            ACTION_DELETE->delete()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun create(){
        Log.d("서비스","create()실행")
    }
    fun delete() {
        Log.d("서비스","delete()실행")
    }
}
