package with.dee2.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        val listener= object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val current_text=p0.toString()
                if(current_text.length >=8){
                    Log.d("tag","$current_text")
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        }
       View.OnClickListener {  }
        editText1.addTextChangedListener(listener)
        radioGroup.setOnCheckedChangeListener{group,checked->
            when(checked){
                R.id.radioApple -> Log.d("result","사과")
                R.id.radioBanana->Log.d("result","바나나")
            }
        }

        button3.setOnClickListener {
            val intent = Intent()

        }
    }
}