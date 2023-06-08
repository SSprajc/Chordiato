package hr.algebra.chordiato.domain.use_case

import hr.algebra.chordiato.core.util.Resource
import hr.algebra.chordiato.data.remote.repository.TrackRemoteRepositoryImpl
import hr.algebra.chordiato.data.util.toTrack
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.repository.TrackRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecognizeSongUseCase() {

    private val repository: TrackRemoteRepository = TrackRemoteRepositoryImpl()

    operator fun invoke(encodedSample: String): Flow<Resource<Track>> = flow {
        try {
            emit(Resource.Loading())
            val track = repository.getSong(encodedSample).toTrack()
            emit(Resource.Success(track))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}