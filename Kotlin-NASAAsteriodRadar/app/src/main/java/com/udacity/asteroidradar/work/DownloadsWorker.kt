package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.utils.DateUtils
import com.udacity.asteroidradar.utils.toFormattedString
import com.udacity.asteroidradar.database.AppDatabase.Companion.getDatabase
import com.udacity.asteroidradar.room.AsteroidsRepository
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import timber.log.Timber

class DownloadsWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidsRepository(database.asteroidDao())

        try {
            repository.deleteOldAsteroids()
            repository.getAsteroidsRemotely(
                DateUtils.getToday().toFormattedString(),
                DateUtils.getDateAfter(Constants.DEFAULT_END_DATE_DAYS).toFormattedString()
            ).first().forEach {
                repository.saveAsteroid(it)
            }
            Timber.d("WorkManager: Work request for syncing asteroids is run.")
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }

    companion object {
        const val WORK_NAME = "DownloadsWorker"
    }
}
