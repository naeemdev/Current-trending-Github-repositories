package com.gojek_assignment_android.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {

    private val TAG = ApiClient::class.java.simpleName
    private var retrofitApi: Retrofit? = null
    private var str_baseurl = "https://github-trending-api.now.sh/"


    val apiClient: Retrofit?
        get() {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val orignalRequest = chain.request()

                    val newRequest = orignalRequest.newBuilder()
                        .header("Content-Type", "application/json")

                        .build()
                    return chain.proceed(newRequest)

                }
            })

            val logging = HttpLoggingInterceptor()
            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
            // logging.apply { logging.level = HttpLoggingInterceptor.Level.HEADERS }

            httpClient.addInterceptor(logging)
            httpClient.readTimeout(120, TimeUnit.SECONDS)

            if (retrofitApi == null) {
                retrofitApi = Retrofit.Builder()
                    .baseUrl(str_baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofitApi
        }
}