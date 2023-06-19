package prototype.todolist.repositories

import prototype.todolist.dao.FavoritesDAO
import prototype.todolist.models.Favorite

class FavoritesRepository () {

    private val favoritesDao = FavoritesDAO()

    suspend fun getFavorites() = favoritesDao.getFavorites()
}