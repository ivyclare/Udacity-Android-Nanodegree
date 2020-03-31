package com.udacity.shoestore

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class ShoeDetailFragmentDirections private constructor() {
  companion object {
    fun actionShoeDetailFragmentToShoesListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_shoeDetailFragment_to_shoesListFragment)
  }
}
