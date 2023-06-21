package prototype.todolist.dao.api

import prototype.todolist.models.Favorite
import prototype.todolist.models.Video
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface FavsApiInterface {

    @GET("favorite")
    suspend fun getFavorites(): List<Favorite>

    @POST("favorite/store")
    suspend fun save(@Body video : Video) : Response<Any>
    // suspend fun save(@Field("video_id") video_id: String) : Response<Any>
}
