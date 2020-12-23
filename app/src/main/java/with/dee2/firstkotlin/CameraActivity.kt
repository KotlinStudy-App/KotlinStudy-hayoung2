package with.dee2.firstkotlin

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camera.*
import java.util.jar.Manifest

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnCamera.setOnClickListener{
            checkCameraPermission()
        }
    }

    fun checkCameraPermission () {
        val cameraPermission =ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)

        if(cameraPermission==PackageManager.PERMISSION_GRANTED){
            startProcess()
        }else{
            requesrPermission()
        }
    }

    fun startProcess() {
        Toast.makeText(this,"카메라 사용 권한이 승인되었습니다.",Toast.LENGTH_SHORT).show()
    }

    val FLAG_CAMERA=99
    fun requesrPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),FLAG_CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            FLAG_CAMERA->{
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    startProcess()
                }else{
                    finish()
                }
            }
        }
    }
}