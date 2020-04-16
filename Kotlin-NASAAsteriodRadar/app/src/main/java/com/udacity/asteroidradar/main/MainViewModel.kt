package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.room.AppRoomDatabase
import com.udacity.asteroidradar.room.AsteroidsRepository
import com.udacity.asteroidradar.utils.AsteroidWebService
import com.udacity.asteroidradar.utils.TimeUtils
import kotlinx.coroutines.*
import org.json.JSONObject
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository: AsteroidsRepository
    val webService = AsteroidWebService.create()
    val _allAsteroids = MutableLiveData<List<Asteroid>>()
    var imageOfTheDay = MutableLiveData<PictureOfDay>()

    val job = Job()
    var uiscope = CoroutineScope(Dispatchers.Main + job)

    val allAsteroids: LiveData<List<Asteroid>>
        get() = _allAsteroids

    init {
        val database = AppRoomDatabase.getDatabase(application)
        val dao = database.asteroidDao()
        repository = AsteroidsRepository(dao)

        getUpdatedAsteriods()
        getImageOfTheDay()
    }

    suspend fun getNewAsteroids(): List<Asteroid>? {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                    webService.getAsteroids(BuildConfig.API_KEY, TimeUtils.getTodayDate())

                if (response.isSuccessful) {
                    return@withContext parseAsteroidsJsonResult(JSONObject(response.body()))
                } else return@withContext emptyList<Asteroid>()
            } catch (ex: UnknownHostException) {
                Timber.d("No internet")
                return@withContext emptyList<Asteroid>()
            } catch (connectEx: SocketTimeoutException) {
                Timber.d("Socket time out ${connectEx.message}")
                return@withContext emptyList<Asteroid>()
            } catch (ex: Exception) {
                Timber.d("Exception ${ex.message}")
                return@withContext emptyList<Asteroid>()
            }

        }

    }

    fun getUpdatedAsteriods() {
        uiscope.launch {
            try {
                val res = getNewAsteroids()

                if (res != null) {
                    _allAsteroids.value = res
                } else {
                    _allAsteroids.value = repository.getAsteroidsLiveData().value

                }
            } catch (e: Exception) {

                val asteroidsData = repository.getAsteroidsLiveData().value
                if (asteroidsData != null) {
                    _allAsteroids.value = asteroidsData
                } else {
                    _allAsteroids.value = emptyList()
                }
            }
        }
    }

    fun getImageOfTheDay() {

        try {
            uiscope.launch {

                val imageResponse = repository.getImageOfTheDay()
                if (imageResponse.isSuccessful) {
                    imageOfTheDay.value = imageResponse.body()!!
                }
            }

        } catch (unkHost: UnknownHostException) {

        } catch (socketTO: SocketTimeoutException) {
            Timber.d("Timed out ${socketTO.message}")
        } catch (ex: Exception) {

        }

    }

}