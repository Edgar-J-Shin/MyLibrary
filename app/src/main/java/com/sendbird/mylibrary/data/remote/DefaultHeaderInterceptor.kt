package com.sendbird.mylibrary.data.remote

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultHeaderInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // header
        getDefaultHeader().forEach { (key, value) ->
            builder.addHeader(key, value)
        }

        return chain.proceed(builder.build())
    }

    /**
     * Rest API 에서 사용하는 default headers
     */
    private fun getDefaultHeader(): Map<String, String> = mapOf(
        "Content-Type" to "application/json"
    )
}