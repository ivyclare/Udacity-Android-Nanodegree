package com.udacity.asteroidradar.room

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.AsteroidDao
import kotlinx.coroutines.flow.Flow

class AsteroidsRepository(asteroidDao: AsteroidDao) {

    private val localAsteroidsData = LocalAsteroidsData(asteroidDao)
    private val remoteAsteroidsData = RemoteAsteroidsData()

    fun getWeeklyAsteroids(): Flow<List<Asteroid>> =
        localAsteroidsData.getWeeklyAsteroids()

    fun getTodayAsteroids(): Flow<List<Asteroid>> =
        localAsteroidsData.getTodayAsteroids()

    fun getSavedAsteroids(): Flow<List<Asteroid>> =
        localAsteroidsData.getSavedAsteroids()

    suspend fun saveAsteroid(asteroid: Asteroid) =
        localAsteroidsData.saveAsteroid(asteroid)

    suspend fun deleteOldAsteroids() =
        localAsteroidsData.deleteOldAsteroids()

    fun getAsteroidsRemotely(startDate: String, endDate: String): Flow<List<Asteroid>> =
        remoteAsteroidsData.getAsteroids(startDate, endDate)

    fun getImageOfTheDay(): Flow<PictureOfDay> =
        remoteAsteroidsData.getImageOfTheDay()
}
