package com.udacity.shoestore

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class ShoeListFragmentDirections private constructor() {
  companion object {
    fun actionShoeListFragmentToShoeDetailFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_shoeListFragment_to_shoeDetailFragment)
  }
}
