package id.phephen.mvvmhiltcoroutines.data.remote

import id.phephen.mvvmhiltcoroutines.model.DogResponse
import id.phephen.mvvmhiltcoroutines.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Phephen on 30/05/2022.
 */
interface DogService {

    @GET(Constants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>

}