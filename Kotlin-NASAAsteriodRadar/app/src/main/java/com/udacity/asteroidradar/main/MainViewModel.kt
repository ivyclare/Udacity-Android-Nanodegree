package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.utils.NetworkState
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.AppDatabase.Companion.getDatabase
import com.udacity.asteroidradar.room.AsteroidsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var asteroidsRepository = AsteroidsRepository(getDatabase(application).asteroidDao())

    private val _asteroidsLiveData = MutableLiveData<List<Asteroid>>()
    val asteroidsLiveData: LiveData<List<Asteroid>>
        get() = _asteroidsLiveData

    private val _asteroidsStateLiveData = MutableLiveData<NetworkState>()
    val asteroidsStateLiveData: LiveData<NetworkState>
        get() = _asteroidsStateLiveData

    private val _imageOfTheDayLiveData = MutableLiveData<PictureOfDay>()
    val imageOfTheDayLiveData: LiveData<PictureOfDay>
        get() = _imageOfTheDayLiveData

    private val _imageStateLiveData = MutableLiveData<NetworkState>()
    val imageStateLiveData: LiveData<NetworkState>
        get() = _imageStateLiveData

    init {
        getWeeklyAsteroids()
        getImageOfTheDay()
    }

    private suspend fun Flow<List<Asteroid>>.observeChanges() = onStart {
        _asteroidsStateLiveData.postValue(NetworkState.LOADING)
    }.catch {
        _asteroidsStateLiveData.postValue(NetworkState.error(Constants.FAILED_TO_LOAD_ASTEROIDS))
        Timber.e(it)
    }.collect {
        _asteroidsLiveData.postValue(it)
        _asteroidsStateLiveData.postValue(NetworkState.LOADED)
    }

    fun getWeeklyAsteroids() = viewModelScope.launch {
        asteroidsRepository.getWeeklyAsteroids().observeChanges()
    }

    fun getTodayAsteroids() = viewModelScope.launch {
        asteroidsRepository.getTodayAsteroids().observeChanges()
    }

    fun getSavedAsteroids() = viewModelScope.launch {
        asteroidsRepository.getSavedAsteroids().observeChanges()
    }

    private fun getImageOfTheDay() = viewModelScope.launch {
        asteroidsRepository.getImageOfTheDay().onStart {
            _imageStateLiveData.postValue(NetworkState.LOADING)
        }.catch {
            _imageStateLiveData.postValue(NetworkState.error(Constants.FAILED_TO_LOAD_IOD))
            Timber.e(it)
        }.collect {
            _imageOfTheDayLiveData.postValue(it)
        }
    }

    fun setImageState(networkState: NetworkState) {
        _imageStateLiveData.postValue(networkState)
    }
}
