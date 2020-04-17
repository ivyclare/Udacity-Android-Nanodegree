package com.udacity.asteroidradar.room

import com.udacity.asteroidradar.api.AsteroidWebService
import com.udacity.asteroidradar.api.RetrofitClient
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class RemoteAsteroidsData {

    private val api = RetrofitClient.getClient().create(AsteroidWebService::class.java)

    fun getAsteroids(startDate: String, endDate: String): Flow<List<Asteroid>> = flow {
        emit(
            parseAsteroidsJsonResult(
                JSONObject(
                    api.getAsteroids(
                        startDate,
                        endDate
                    )
                )
            )
        )
    }

    fun getImageOfTheDay(): Flow<PictureOfDay> = flow {
        emit(api.getImageOfTheDay())
    }
}
