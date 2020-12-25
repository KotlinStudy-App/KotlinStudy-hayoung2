package with.dee2.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_d_b_connect.*

class DBConnectActivity : AppCompatActivity() {
    val DB_NAME ="sqlite.sql"
    val DB_VERSION =1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_b_connect)

        val helper=SqliteHelper(this,DB_NAME,DB_VERSION)



         val adapter=RecyclerAdapter()
        val memos=helper.selectMemo()
        adapter.listData.addAll(memos)
        recyclerMemo.adapter=adapter
        recyclerMemo.layoutManager=LinearLayoutManager(this)


        buttonSave.setOnClickListener{
            val content =editMemo.text.toString()
            if (content.isNotEmpty()){
                val memo =Memo(null,content,System.currentTimeMillis())
                helper.insertMemo(memo)
                editMemo.setText(" ")
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()
            }
        }
    }
}