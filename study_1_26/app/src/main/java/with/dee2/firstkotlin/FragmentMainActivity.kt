package with.dee2.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FragmentMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_main)

        setFragment()
    }

    fun setFragment() {
        //삽입할 프래그먼트 생성
        val fragment =ListFragment()
        // 삽입 트랜잭션 시작
        val transaction =supportFragmentManager.beginTransaction()
        // 트랜잭션을 통해서 프ㅐ그먼트 삽입
        transaction.add(R.id.frameLayout,fragment)
        //커밋
        transaction.commit()
    }

    fun goDetail() {
        //삽입할 프래그먼트 생성
        val fragment =DetailFragment()
        // 삽입 트랜잭션 시작
        val transaction =supportFragmentManager.beginTransaction()
        // 트랜잭션을 통해서 프ㅐ그먼트 삽입
        transaction.add(R.id.frameLayout,fragment)
            //e뒤로 가기 버튼을 사용하기 위한 처리
        transaction.addToBackStack("detail")
        //커밋
        transaction.commit()
    }
}