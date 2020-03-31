package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import java.util.ArrayList

class ShoeListViewModel: ViewModel() {
    private var shoeList = listOf<Shoe>()

    private val _shoes: MutableLiveData<List<Shoe>> = MutableLiveData()
    val shoes: LiveData<List<Shoe>> = _shoes


    fun addShoe(shoe: Shoe) {
        shoeList = shoeList.plus(shoe)
        _shoes.value = shoeList
    }
}
