package id.phephen.mvvmhiltcoroutines.data.remote

import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.phephen.mvvmhiltcoroutines.model.BaseApiResponse
import id.phephen.mvvmhiltcoroutines.model.DogResponse
import id.phephen.mvvmhiltcoroutines.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Phephen on 30/05/2022.
 */

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getDog(): Flow<NetworkResult<DogResponse>> {
        return flow<NetworkResult<DogResponse>>{
            emit(safeApiCall { remoteDataSource.getDog() })
        }.flowOn(Dispatchers.IO)
    }

}