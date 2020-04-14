package com.udacity.shoestore

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class WelcomeFragmentDirections private constructor() {
  companion object {
    fun actionWelcomeFragmentToInstructionsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_welcomeFragment_to_instructionsFragment)
  }
}
