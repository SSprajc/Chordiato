package hr.algebra.chordiato.data.remote.api.shazam

import okhttp3.Interceptor
import okhttp3.Response

class ShazamInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("content-type", "text/plain")
        return chain.proceed(requestBuilder.build())
    }
}