package hr.algebra.chordiato.data.remote.api.shazam

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShazamService {
    private lateinit var shazamApi: ShazamApi

    fun getShazamApi() : ShazamApi {
        if(!::shazamApi.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(SHAZAM_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient())
                .build()
            shazamApi = retrofit.create(ShazamApi::class.java)
        }
        return shazamApi
    }

    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ShazamInterceptor())
            .build()
    }
}