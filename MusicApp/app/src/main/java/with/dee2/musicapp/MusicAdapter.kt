package with.dee2.musicapp

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import java.text.SimpleDateFormat

class MusicAdapter: RecyclerView.Adapter<MusicAdapter.Holder>() {

    val musicList= mutableListOf<Music>()
    var mediaPlayer:MediaPlayer? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
       return musicList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music=musicList[position]
        holder.setMusic(music)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var musicUri: Uri? = null


        init {
            itemView.setOnClickListener {
                if (mediaPlayer != null) {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }

        fun setMusic(music: Music) {
            musicUri = music.getMusicUri()
            itemView.imageAlbum.setImageURI(music.getAlbumUri())
            itemView.textArtist.text = music.artist
            itemView.textTitle.text = music.title
            val sdf = SimpleDateFormat("mm:ss")
            itemView.textDuration.text = sdf.format(music.duration)
        }

    }
}

