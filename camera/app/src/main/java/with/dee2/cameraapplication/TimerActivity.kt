package with.dee2.cameraapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_timer.*
import kotlin.concurrent.thread

class TimerActivity : AppCompatActivity() {
    var total =0
    var started =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        buttonStart.setOnClickListener{
            start()
        }
        buttonStop.setOnClickListener{
            stop()
        }
        buttonPause.setOnClickListener {
            pause()
        }
    }
    val SET_TIME =51
    // 서브thread 등장
    val handler =object : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                SET_TIME->{
                    val t=msg.arg1
                    textTimer.text=formatTime(t)
                }
            }

        }
    }

    fun start() {
        started=true
        thread(start=true){
            while (started){
                Thread.sleep(1000)
                total=total+1
                //msg로 받음
              // handler.sendEmptyMessage(total)
// 아래 방식으로 보냄 위에도 되는데 보내는 방식 이런 식으로
                val msg=Message()
                msg.what=SET_TIME
                msg.arg1=total
                handler.sendMessage(msg)
            }
        }
    }
    fun stop() {
        started=false
    }
    fun pause() {
        started=false
        total=0
        textTimer.text="00 : 00"
    }

    fun formatTime(time:Int) :String {
        val minute =String.format("%02d",time/60)
        val second= String.format("%02d",time%60)
        return "$minute : $second"
    }
}