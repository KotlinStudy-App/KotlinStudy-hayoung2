package with.dee2.translate

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class JsonData {
    @SerializedName("userId")
    val userId: String? = null

    @SerializedName("id")
    val id: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("body")
    val body: String? = null
}

interface API {
    @GET("/posts/{index}")
    fun getsjson(@Path("index") index: String?): Call<JsonData>}
