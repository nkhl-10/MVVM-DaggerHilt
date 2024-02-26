package mvvm.app.dragerhill.network

import mvvm.app.dragerhill.model.post
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPost():List<post>
}