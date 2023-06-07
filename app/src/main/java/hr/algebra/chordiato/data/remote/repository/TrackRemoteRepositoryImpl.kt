package hr.algebra.chordiato.data.remote.repository

import hr.algebra.chordiato.data.remote.api.serp.*
import hr.algebra.chordiato.data.remote.api.shazam.ShazamApi
import hr.algebra.chordiato.data.remote.api.shazam.ShazamService
import hr.algebra.chordiato.data.remote.dto.serp.SerpResponseDto
import hr.algebra.chordiato.data.remote.dto.shazam.ShazamResponseDto
import hr.algebra.chordiato.domain.repository.TrackRemoteRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class TrackRemoteRepositoryImpl() : TrackRemoteRepository {

    private val shazamApi: ShazamApi = ShazamService().getShazamApi()
    private val serpApi: SerpApi = SerpService().getSerpApi()

    override suspend fun getSong(encodedSample: String): ShazamResponseDto {
        val mediaType = "text/plain".toMediaTypeOrNull()
        val requestBody = encodedSample.toRequestBody(mediaType)
        return shazamApi.detect(requestBody)
    }

    override suspend fun getChordsLink(songName: String, artist: String): SerpResponseDto {
        val query = "$songName $artist chords"
        return serpApi.internetSearch(SERP_API_SEARCH_ENGINE,
            SERP_API_KEY,
            query,
            SERP_API_RESULT_PAGES)
    }
}