package hr.algebra.chordiato.data.remote.api.serp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SerpService {
    private lateinit var serpApi: SerpApi

    fun getSerpApi(): SerpApi {
        if (!::serpApi.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(SERP_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            serpApi = retrofit.create(SerpApi::class.java)
        }
        return serpApi
    }
}