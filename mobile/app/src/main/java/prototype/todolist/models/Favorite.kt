package prototype.todolist.models

import com.google.gson.annotations.SerializedName

data class Favorite  (
    var id: Int,
    @SerializedName("title")
    var video_id: String,
    var video_title: String,
    var video_description: String,
    var video_thumb: String,
    var timestamp: Long
)

