package prototype.todolist.ui


import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import prototype.todolist.R
import prototype.todolist.databinding.FragmentTaskManagerBinding
import prototype.todolist.utils.Status


class FavManagerFragment : BaseFragment<FragmentTaskManagerBinding>(FragmentTaskManagerBinding::inflate) {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavRecyclerViewAdapter

    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)
        adapter =  FavRecyclerViewAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =  adapter
        }

        // getUsers observe
        viewModel.getFavorites().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> this.showProgressBar()
                Status.ERROR -> this.showResponseError(it.message.toString())
                Status.SUCCESS -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.apply {
                        addTasks(it.data!!)
                        notifyDataSetChanged()
                    }
                }
            }
        })

    }

    override fun listeners(view: View) {
        binding.apply {
        }
    }



}