package id.phephen.mvvmhiltcoroutines.data.remote

import javax.inject.Inject

/**
 * Created by Phephen on 30/05/2022.
 */
class RemoteDataSource @Inject constructor(private val dogService: DogService) {

    suspend fun getDog() =
        dogService.getDog()

}