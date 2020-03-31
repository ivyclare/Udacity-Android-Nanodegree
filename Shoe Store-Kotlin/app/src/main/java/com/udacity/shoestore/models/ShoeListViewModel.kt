package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import java.util.ArrayList

class ShoeListViewModel : ViewModel() {
    private val shoes: MutableLiveData<MutableList<Shoe>> = MutableLiveData()

    init {
        shoes.value = ArrayList()
    }

    fun getShoes(): LiveData<MutableList<Shoe>> = shoes
}