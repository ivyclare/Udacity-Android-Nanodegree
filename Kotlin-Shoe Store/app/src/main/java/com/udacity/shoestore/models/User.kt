package com.udacity.shoestore.models

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import java.util.ArrayList

class User : ViewModel() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    private val client: MutableLiveData<User> = MutableLiveData()

    fun getClient(): LiveData<User> = client
}