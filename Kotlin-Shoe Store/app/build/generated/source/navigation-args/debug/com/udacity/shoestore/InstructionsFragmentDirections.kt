package com.udacity.shoestore

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class InstructionsFragmentDirections private constructor() {
  companion object {
    fun actionInstructionsFragmentToShoeListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_instructionsFragment_to_shoeListFragment)
  }
}
