package hr.algebra.chordiato.domain.repository

import hr.algebra.chordiato.data.remote.dto.serp.SerpResponseDto
import hr.algebra.chordiato.data.remote.dto.shazam.ShazamResponseDto
import hr.algebra.chordiato.domain.model.Track
import okhttp3.RequestBody

interface TrackRemoteRepository {

    suspend fun getSong(encodedSample: String) : ShazamResponseDto

    suspend fun getChordsLink(songName: String, artist: String) : SerpResponseDto
}