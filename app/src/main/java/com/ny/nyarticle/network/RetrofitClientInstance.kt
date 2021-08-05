package com.ny.nyarticle.network

import com.google.gson.GsonBuilder
import com.ny.nyarticle.utils.CONSTANTS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientInstance {
    private lateinit var retrofit: Retrofit
    private var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()
/*Retrofit Client*/
    fun getClient(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

        return this.retrofit
    }
}