package prototype.todolist.ui

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


class FavRecyclerViewAdapter(private val tasks: ArrayList<Favorite>, navController: NavController )
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

    override fun getItemCount(): Int  = tasks.size

    override fun onBindViewHolder(dataViewHolder: DataViewHolder, position: Int) {

        val task = tasks[position]
        dataViewHolder.bind(task)

        dataViewHolder.cardView.setOnClickListener {
            // update
            //val action = FavManagerFragmentDirections.actionTaskManagerFragmentToTaskFormFragment(taskid = task.id )
            //navController.navigate(action)
            Toast.makeText(it.context, "Clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    fun addTasks(tasks: List<Favorite>) {
        this.tasks.apply {
            clear()
            addAll(tasks)
        }

    }


}