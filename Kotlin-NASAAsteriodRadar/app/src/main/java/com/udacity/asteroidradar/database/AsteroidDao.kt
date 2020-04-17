package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroids ORDER BY epochDateCloseApproach")
    fun getAllAsteroids(): Flow<List<Asteroid>>

    @Query(
        """
        SELECT * FROM asteroids
        WHERE epochDateCloseApproach BETWEEN :startEpochDateCloseApproach AND :endEpochDateCloseApproach
        ORDER BY epochDateCloseApproach
        """
    )
    fun getAsteroidsBetween(
        startEpochDateCloseApproach: Long,
        endEpochDateCloseApproach: Long
    ): Flow<List<Asteroid>>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate == :todayFormattedDate ORDER BY epochDateCloseApproach")
    fun getAsteroidsToday(todayFormattedDate: String): Flow<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(asteroid: Asteroid)

    @Query("DELETE FROM asteroids WHERE epochDateCloseApproach < :startDate")
    suspend fun deleteAsteroidsBefore(startDate: Long)
}