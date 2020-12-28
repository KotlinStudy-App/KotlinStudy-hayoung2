package with.dee2.cameraapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class Foreground : Service() {

    override fun onBind(intent: Intent): IBinder {
    return Binder()
    }
}
