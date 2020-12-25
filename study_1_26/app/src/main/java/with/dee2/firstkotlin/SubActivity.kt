package with.dee2.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val data= listOf("선택하세요 ","월","화","수","목","금")
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        spinner2.adapter=adapter
        spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem=data[p2]
                value.text=selectedItem
            }

        }

        value.text=intent.getStringExtra("content")

        btn4.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("value","돌려준 값")
            startActivity(intent)
        }
    }
}