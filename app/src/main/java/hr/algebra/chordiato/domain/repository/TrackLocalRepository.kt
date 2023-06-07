package hr.algebra.chordiato.domain.repository

import hr.algebra.chordiato.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackLocalRepository {

    //nemoraju bit suspend kad je flow
    fun getAllTracks() : Flow<List<Track>>

    fun getFavouriteTracks() : Flow<List<Track>>

    suspend fun insertTrack(track: Track) : Long

    suspend fun updateTrack(track: Track)

}