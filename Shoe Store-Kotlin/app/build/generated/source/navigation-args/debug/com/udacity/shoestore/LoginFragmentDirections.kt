package com.udacity.shoestore

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class LoginFragmentDirections private constructor() {
  companion object {
    fun actionLoginFragmentToWelcomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_welcomeFragment)

    fun actionLoginFragmentToShoesFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_shoesFragment)
  }
}
