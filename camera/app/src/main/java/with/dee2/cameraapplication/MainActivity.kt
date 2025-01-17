package with.dee2.cameraapplication

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    val CAMERA_PERMISSION =arrayOf(Manifest.permission.CAMERA)
    val STORAGE_PERMISSION =arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val FLAG_PERM_CAMERA=98
    val FLAG_PERM_STORAGE=99
    val FLAG_REQ_CAMERA=101
    val FLAG_REQ_STORAGE=102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCamera.setOnClickListener{
            if (isPermission(arrayOf(Manifest.permission.CAMERA))){
                openCamera()
            }else{
                ActivityCompat.requestPermissions(this,CAMERA_PERMISSION,FLAG_PERM_CAMERA)
            }
        }
        buttonGallery.setOnClickListener{
            if (isPermission(STORAGE_PERMISSION)){
openGallery()
            }else{
                ActivityCompat.requestPermissions(this,STORAGE_PERMISSION,FLAG_PERM_STORAGE)
            }
        }
        buttonAsync.setOnClickListener {
            val intent =Intent(this,AsyncTestActivity::class.java)
            startActivity(intent)
        }

        buttonTimer.setOnClickListener {
            val intent=Intent(this,TimerActivity::class.java)
            startActivity(intent)
        }

        buttonService.setOnClickListener {
            val intent=Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }

    }

    fun isPermission(permissions:Array<String>):Boolean {

        for (permission in permissions) {
            val result = ContextCompat.checkSelfPermission(this, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
    fun openCamera() {
val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,FLAG_REQ_CAMERA)
    }

    fun openGallery() {
        val intent= Intent(Intent.ACTION_PICK)
        intent.type=MediaStore.Images.Media.CONTENT_TYPE

        startActivityForResult (intent,FLAG_REQ_STORAGE)
    }

    fun serviceStart(view: View){
        val intent =Intent(this,Foreground::class.java)
        ContextCompat.startForegroundService(this,intent)

    }

    fun serviceStop(view: View) {
        val intent =Intent(this,Foreground::class.java)
       stopService(intent)
    }

    fun saveImageFile(filename:String, mimeType:String,bitmap:Bitmap) : Uri? {
        var values=ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
        values.put(MediaStore.Images.Media.MIME_TYPE,mimeType)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            values.put(MediaStore.Images.Media.IS_PENDING,1)
        }
        val url=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        try{
        if (url!=null){
            var descriper=contentResolver.openFileDescriptor(url,"w")
            if (descriper!=null){
                val fos=FileOutputStream(descriper.fileDescriptor)
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
                fos.close()
                return url
            }
        }}catch (e:Exception){
            Log.e("Camera","${e.localizedMessage}")
        }
        return  null
    }

    fun newFileName() :String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename=sdf.format(System.currentTimeMillis())
        return filename
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            when(requestCode){
FLAG_REQ_CAMERA-> {
    if (data?.extras?.get("data")!=null ) {
        val bitmap = data?.extras?.get("data") as Bitmap

        val filename = newFileName()
        val uri = saveImageFile(filename, "image/jpg", bitmap)
        imagePreview.setImageURI(uri)
    }
}
                FLAG_REQ_STORAGE->{
                    val uri =data?.data
                    imagePreview.setImageURI(uri)
                }
            }

        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
FLAG_PERM_CAMERA->{
    var checked=true
    for (grant in grantResults){

        if (grant!=PackageManager.PERMISSION_GRANTED){
            checked=false
            break
        }
    }
    if (checked){
openCamera()
    }
}
        }
    }
}