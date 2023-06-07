package hr.algebra.chordiato.data.local.repository

import hr.algebra.chordiato.core.Application
import hr.algebra.chordiato.data.local.TrackDao
import hr.algebra.chordiato.data.util.toTrack
import hr.algebra.chordiato.data.util.toTrackEntity
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.repository.TrackLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrackLocalRepositoryImpl(
    val application: Application
) : TrackLocalRepository {

    private var dao: TrackDao = application.provideDatabase().trackDao

    override fun getAllTracks(): Flow<List<Track>> = flow {
        emit(dao.getAllTracks().map { it.toTrack() })
    }

    override fun getFavouriteTracks(): Flow<List<Track>> = flow {
        emit(dao.getFavouriteTracks().map { it.toTrack() })
    }

    override suspend fun insertTrack(track: Track): Long {
        return dao.insertTrack(track.toTrackEntity())
    }

    override suspend fun updateTrack(track: Track) {
        dao.updateTrack(track.toTrackEntity())
    }
}