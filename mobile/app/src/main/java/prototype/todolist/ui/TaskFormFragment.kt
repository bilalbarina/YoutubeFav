package prototype.todolist.ui


import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import prototype.todolist.R
import prototype.todolist.models.Favorite
import prototype.todolist.databinding.FragmentTaskFormBinding
import prototype.todolist.utils.Status

class TaskFormFragment : BaseFragment<FragmentTaskFormBinding>(FragmentTaskFormBinding::inflate) {

    companion object {
        val TASKID = "taskid" // Il resemble à une variable static
    }

    private val viewModel: FavoriteViewModel by viewModels()
    private var taskId =  0 // La valeur 0 signifie que le formulaire est dans l'état d'insertion
    private  var task : Favorite? = null

    override fun init(view: View) {

        arguments?.let {
            taskId = it.getInt(TASKID)
        }

        this.setProgressBar(R.id.progressBar)

        // Add
        /*if(taskId == 0){
            this.task = Favorite(0,"",0, System.currentTimeMillis())
            binding.btnDelete.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.GONE
        }
        // Update
        else
        {

            // Call : FindById
            viewModel.findById(taskId).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> this.showProgressBar()
                    Status.ERROR -> this.showResponseError(it.message.toString())
                    Status.SUCCESS -> {
                        this.hideProgressBar()
                        binding.apply {
                            task = it.data!!
                            editTaskTitle.setText(task?.title)
                            spinner.setSelection(task?.priority!!)
                        }
                    }
                }
            })

        }
*/

    }

    override fun listeners(view: View) {
        binding.apply {
            btnSave.setOnClickListener {
                if(TextUtils.isEmpty(editTaskTitle.text)){
                    Toast.makeText(context, "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val taskTitle = editTaskTitle.text.toString()
                val priority = spinner.selectedItemPosition
                val task = Favorite(
                    taskId,
                    "",
                    "",
                    "",
                    "",
                    System.currentTimeMillis()
                )

            }
        }

    }


}
