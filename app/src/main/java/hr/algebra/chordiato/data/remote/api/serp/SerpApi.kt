package hr.algebra.chordiato.data.remote.api.serp

import hr.algebra.chordiato.data.remote.dto.serp.SerpResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val SERP_API_URL = "https://serpapi.com/"
const val SERP_API_KEY = "444e6b54a4e8722173dde40f9d16c2b50c516481e427e654419b67ed636f891c"
const val SERP_API_SEARCH_ENGINE = "google"
const val SERP_API_RESULT_PAGES = 1

interface SerpApi {
    @GET("search.json")
    suspend fun internetSearch(
        @Query("engine") engine: String,
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("num") num: Int
    ) : SerpResponseDto
}