package hr.algebra.chordiato.domain.repository

import hr.algebra.chordiato.data.remote.dto.serp.SerpResponseDto
import hr.algebra.chordiato.data.remote.dto.shazam.ShazamResponseDto

interface TrackRemoteRepository {

    suspend fun getSong(encodedSample: String): ShazamResponseDto

    suspend fun getChordsLink(songName: String, artist: String): SerpResponseDto

}