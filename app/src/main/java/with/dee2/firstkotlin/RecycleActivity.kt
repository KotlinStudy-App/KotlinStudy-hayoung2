package with.dee2.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycle.*

class RecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        //데이터 로딩
        val data=loadData()
        //어댑터 생성
        val adapter =CustomAdapter()
        //어댑터 데이터 전달
        adapter.listData =data
        // 화면에 있는 리사이클러뷰에 어댑터 연결
        recycler.adapter=adapter
        //레이아웃매니저 연결
        recycler.layoutManager=LinearLayoutManager(this)
    }
    fun loadData():MutableList<MemoDB>{
        val data:MutableList<MemoDB> = mutableListOf()
        for(no in 1..20){
            val title ="이것이 안드로이드다 ${no} !"
            val date=System.currentTimeMillis()

            val memo=MemoDB(no,title,date)
            data.add(memo)

        }
        return data
    }
}