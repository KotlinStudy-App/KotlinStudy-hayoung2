package with.dee2.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycle_sub.*

class RecycleSubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_sub)
        textNo.text=intent.getStringExtra("no");
        textTitle.text=intent.getStringExtra("title")
        textDate.text=intent.getStringExtra("date")
    }
}