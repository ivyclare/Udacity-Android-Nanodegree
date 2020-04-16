package com.udacity.asteroidradar.room

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.utils.TimeUtils
import com.udacity.asteroidradar.utils.AsteroidWebService
import timber.log.Timber

class AsteroidsRepository(val asteroidDao: AsteroidDao) {
    private val client:AsteroidWebService
    get() = AsteroidWebService.create()

    suspend fun getAsteroidsFromInternet() = client.getAsteroids(BuildConfig.API_KEY,TimeUtils.getTodayDate())

    suspend fun insertAsteroid(asteroids:ArrayList<Asteroid>) {
        for (asteroid in asteroids) {
            Timber.d("Saving... $asteroid")
            asteroidDao.insertAsteroid(asteroid)
        }
    }

    fun getAsteroidsLiveData() = asteroidDao.getAllAsteroids()

    suspend fun getImageOfTheDay() = client.getImageOfTheDay(BuildConfig.API_KEY)

}