package com.udacity.asteroidradar.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AsteroidWebService {


    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("api_key") apiKey: String, @Query("start_date") startDate:String):Response<String>
    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey:String):Response<PictureOfDay>

    companion object {

        private val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private fun create(httpUrl: HttpUrl): AsteroidWebService = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(httpUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AsteroidWebService::class.java)

        fun create(): AsteroidWebService = create(HttpUrl.parse(Constants.BASE_URL)!!)
    }
}