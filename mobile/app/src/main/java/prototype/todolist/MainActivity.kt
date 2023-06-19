package prototype.todolist


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import prototype.todolist.dao.FavoritesDAO
import prototype.todolist.databinding.ActivityMainBinding
import prototype.todolist.models.Video


/**
 * Main Activity and entry point for the app.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the activity was started by sharing text
        if (Intent.ACTION_SEND == intent.action && intent.type != null && "text/plain" == intent.type) {
            // Get the shared text
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)

            // Do something with the shared text
            if (sharedText != null) {
                val videoId = sharedText.replace("https://youtu.be/", "")
                val video = Video(videoId)

                GlobalScope.launch {
                    FavoritesDAO().save(video)
                                                                                                                                                                                                                   delay(3000)
                }

                Toast.makeText(this, "Added to favorites.", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        // setupActionBarWithNavController(navController)
    }

    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}