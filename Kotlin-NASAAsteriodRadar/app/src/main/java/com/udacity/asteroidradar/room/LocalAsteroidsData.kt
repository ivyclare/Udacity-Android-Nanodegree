package com.udacity.asteroidradar.room

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.utils.DateUtils
import com.udacity.asteroidradar.utils.toFormattedString
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.AsteroidDao
import kotlinx.coroutines.flow.Flow

class LocalAsteroidsData(private val asteroidDao: AsteroidDao) {

    fun getWeeklyAsteroids(): Flow<List<Asteroid>> =
        asteroidDao.getAsteroidsBetween(
            DateUtils.getToday().time,
            DateUtils.getDateAfter(Constants.DEFAULT_END_DATE_DAYS).time - 1
        )

    fun getTodayAsteroids(): Flow<List<Asteroid>> =
        asteroidDao.getAsteroidsToday(DateUtils.getToday().toFormattedString())

    fun getSavedAsteroids(): Flow<List<Asteroid>> =
        asteroidDao.getAllAsteroids()

    suspend fun saveAsteroid(asteroid: Asteroid) =
        asteroidDao.insert(asteroid)

    suspend fun deleteOldAsteroids() =
        asteroidDao.deleteAsteroidsBefore(DateUtils.getToday().time)
}
