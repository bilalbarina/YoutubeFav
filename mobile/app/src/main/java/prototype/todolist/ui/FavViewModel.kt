package prototype.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import prototype.todolist.models.Favorite
import prototype.todolist.repositories.FavoritesRepository
import prototype.todolist.utils.Resource

class FavoriteViewModel : ViewModel()  {

    private val favoritesRepository = FavoritesRepository()

    fun getFavorites() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = favoritesRepository.getFavorites()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}