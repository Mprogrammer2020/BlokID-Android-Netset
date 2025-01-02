package com.sdk.blokid.network_call

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitHelper {
    companion object {
        private var retrofit: Retrofit? = null
        private const val REQUEST_TIMEOUT = 120
        private var okHttpClient: OkHttpClient? = null

        fun getClient(context: Context): Retrofit {
            if (okHttpClient == null)
                initOkHttp(context)
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://stg-pixel.blokid.com")
                    .client(okHttpClient!!)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }

        private fun initOkHttp(context: Context) {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(interceptor)

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("content-type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Connection", "keep-alive")

                try {
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }catch (e: Exception) {

                    if (e is IOException) {

                        throw e
                    } else {

                        throw IOException(e)
                    }
                }
            }
            httpClient.retryOnConnectionFailure(true)
            okHttpClient = httpClient.build()
        }
    }
}