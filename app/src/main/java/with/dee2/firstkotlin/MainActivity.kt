package with.dee2.firstkotlin

import android.app.Activity
import android.content.Intent
import android.content.SyncRequest
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Console

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    var text:String="dd"
    var bool =1>=2
    var variable:IntArray= IntArray(10)
    // 컬렉션(동적사용,리스트 Generic
    var mutableList= mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myName="hayoung"
        // View 안에 interface로 정의 되어 있어서 정의 해야함.
        // 안에 onClick 함수 있어서 정의해줘야함
        //원리는 저런 식으로 함
        val listener= object: View.OnClickListener {
            override fun onClick(p0: View?) {
                textView.text="listener 클릭"
            }
        }
        var myname = "여정화"
        Log.d(TAG, "my name=$myname")
        myname = "여정화2"
        Log.d(TAG, "my name=$myname")
        val myAge = 19
        Log.d(TAG, "pi=$myAge")

        var numbers = "1,2,3,4,5,6"
        var thisWeekNumbers = "5,6,7,8,9,10"
        if (numbers == thisWeekNumbers){
            textView.text = "당첨되었습니다."
        }else{
            textView.text = "당첨되지않았습니다."
        }

        for(idx in 1..10){
            textView.append("\n현재 숫자는 ${idx} 입니다.")


        }

        button.setOnClickListener{
            val intent=Intent(this,TestActivity::class.java)
            intent.putExtra("param","값")
            startActivity(intent)
        }

        moveSub.setOnClickListener{
            val intent=Intent(this,SubActivity::class.java)
            intent.putExtra("content",textView.text.toString())
            Log.d("dd",textView.text.toString())
            startActivityForResult(intent,99)
        }
        moveRecycle.setOnClickListener{
            val intent =Intent(this,RecycleActivity::class.java)
            startActivity(intent)
        }
        fragmentBtn.setOnClickListener{
            val intent =Intent(this,FragmentMainActivity::class.java)
            startActivity(intent)
        }

        for (idx in 1..9){
            textView.append("\n현재 숫자 ${idx} 입니다 ㅋ")
            Log.d(TAG,"현재 숫자 ${idx} 입니다 ㅋ")
        }
        Log.d("compare","cc dd")
        process1(14);
        var result = process2();
    }
    fun process1(month:Int) {

        // if 확장판
        when(month){
            6,7,8 -> {
                for (i in 0..3){ // for 에 step 은 i+=2 3 4 ,... 이런 거 downTo 값 아래
                    variable[i]=1
                }
            }
            in 9..13-> Log.d("9~13","사이")
            else -> {
                for (i in variable){
                    var mutableMap= mutableMapOf<String,Int>()
                    mutableMap.put("값",1)
                    Log.d("값 ","dd"+i)
                    Log.d("테스트 "," 값 value ${mutableMap.get("값")}")
                }
            }

        }
    }
    fun process2() : Boolean {
        var test = Test()
        test.testFun()
        LogTest.print("태그","test")
        return true
    }
    override fun onActivityResult(requestCode: Int,resultCode:Int, data:Intent?){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode== Activity.RESULT_OK){
            when(requestCode){
                99->{
                    val returnValue=data?.getStringExtra("value") ?:"None"
                    Log.d("activity","$returnValue");
                }
            }

        }
    }
}

class LogTest : Test() {
    //초기화없이 사용 가능.
    var tmp=variable
    override var user="hayoung2"
    companion object {

        fun print(tag: String, message: String) {
            Log.d("[$tag]", "[$message]")
        }
    }
}

open class Test {
    var variable:String=""
    //null도 메모리에 자리를 차지하는 객체이지만 다른 값들처럼 함수가 정의되어있지않아서 에러발생
    //Null Pointer Exception
    // ? safe call null일 경우, 뒤에 코드 실행 안함.
    // ? : value null 일 경우 value로 대체
    var cnt:Int? =null //하나의 값이라서 생성하지않았지만 이미 생성되어있음 특정변수에 null을 넣으면 null을 봄
    open var user="hayoung" //오버라이드하려면 부모 open , 자식 override 해주면 됨

    //오버로드
    fun testFun():Int {
        Log.d("testFun","실행")
        return 0
    }
    fun testFun(param:String):Int {
        Log.d("testFun","[$param]실행")
        return 1
    }

}