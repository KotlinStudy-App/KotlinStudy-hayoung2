package with.dee2.firstkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import java.text.SimpleDateFormat

class CustomAdapter : RecyclerView.Adapter<Holder>() {
    val listData= mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return listData.size

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo=listData.get(position)
        holder.setMemo(memo)
    }

}
class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
    fun setMemo(memo:Memo){
        itemView.textNo.text="${memo.no}"
        itemView.textTitle.text=memo.title
        val sdf=SimpleDateFormat("yyyy/MM/dd")
        val date=sdf.format(memo.timestamp)
        itemView.textDate.text=date

    }
}