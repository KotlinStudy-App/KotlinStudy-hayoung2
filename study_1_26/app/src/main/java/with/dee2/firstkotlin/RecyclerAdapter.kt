package with.dee2.firstkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<Holders>() {
    var listData = mutableListOf<Memo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holders {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)

        return Holders(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holders, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }
}
    class Holders(itemView: View):RecyclerView.ViewHolder(itemView){
        fun  setMemo(memo:Memo) {
            itemView.textNo.text="${memo.no}"
            itemView.textContent.text="${memo.content}"
            val sdf=SimpleDateFormat("yyyy/MM/dd hh:mm")
            val datetime =sdf.format(memo.datetime)
            itemView.textDatetime.text="$datetime"

        }
    }
