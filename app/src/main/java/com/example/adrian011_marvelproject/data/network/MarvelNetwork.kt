package com.example.adrian011_marvelproject.data.network

import com.example.adrian011_marvelproject.BuildConfig
import com.example.adrian011_marvelproject.base.toMD5
import com.example.adrian011_marvelproject.data.model.ResponseAllCharactersDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class MarvelNetwork {
    lateinit var service: MarvelService


    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()

        service = retrofit.create(MarvelService::class.java)
    }


    private fun createHttpClient(): OkHttpClient {
        // Create OkHttpClient
        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        // Logger Interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        // App token
        val hash = ((System.currentTimeMillis()/1000).toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toMD5()

        builder.addInterceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", (System.currentTimeMillis()/1000).toString())
                .build()

            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        return builder.build()
    }

    suspend fun getAllCharacters(): ResponseAllCharactersDataModel {
        loadRetrofit()

        return service.getAllCharacters()
    }


    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}