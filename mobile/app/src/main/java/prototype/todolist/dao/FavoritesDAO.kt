package prototype.todolist.dao

import prototype.todolist.dao.api.FavsApiInterface
import prototype.todolist.models.Favorite
import prototype.todolist.models.Video
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoritesDAO {

    companion object{

        private var BASE_URL = "https://webhook.site/188fde25-4e13-464f-a782-4b8e7f151cd4/"

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //Doesn't require the adapter
        }
        val apiService: FavsApiInterface = getRetrofit().create(FavsApiInterface::class.java)
    }

    suspend fun getFavorites() = apiService.getFavorites()

    suspend fun save(video: Video) = apiService.save(video)
}