package prototype.todolist.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import prototype.todolist.R
import prototype.todolist.models.Favorite


class FavRecyclerViewAdapter(private val videos: ArrayList<Favorite>, navController: NavController )
    : RecyclerView.Adapter<FavRecyclerViewAdapter.DataViewHolder>() {

    private val navController = navController

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val videoThumb: ImageView = view.findViewById<ImageView>(R.id.videoThumb)
        val videoTitle: TextView = view.findViewById<Button>(R.id.videoTitle)
        val cardView: CardView = view.findViewById(R.id.cardview)
        fun bind(favorite: Favorite) {
            Picasso.get().load(favorite.video_thumb).into(videoThumb);
            videoTitle.text = favorite.video_title.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return DataViewHolder(layout)
    }

    override fun getItemCount(): Int  = videos.size

    override fun onBindViewHolder(dataViewHolder: DataViewHolder, position: Int) {

        val video = videos[position]
        dataViewHolder.bind(video)

        dataViewHolder.cardView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.video_id))
            //startActivity(intent)
        }
    }

    fun addTasks(videos: List<Favorite>) {
        this.videos.apply {
            clear()
            addAll(videos)
        }

    }


}