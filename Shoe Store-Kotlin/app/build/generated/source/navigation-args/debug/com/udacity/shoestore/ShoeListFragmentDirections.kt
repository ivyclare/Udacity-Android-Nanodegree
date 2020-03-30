package com.udacity.shoestore

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.udacity.shoestore.models.Shoe
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

class ShoeListFragmentDirections private constructor() {
  private data class ActionShoeListFragmentToShoeDetailFragment(
    val shoe: Shoe
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_shoeListFragment_to_shoeDetailFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun getArguments(): Bundle {
      val result = Bundle()
      if (Parcelable::class.java.isAssignableFrom(Shoe::class.java)) {
        result.putParcelable("shoe", this.shoe as Parcelable)
      } else if (Serializable::class.java.isAssignableFrom(Shoe::class.java)) {
        result.putSerializable("shoe", this.shoe as Serializable)
      } else {
        throw UnsupportedOperationException(Shoe::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      return result
    }
  }

  companion object {
    fun actionShoeListFragmentToShoeDetailFragment(shoe: Shoe): NavDirections =
        ActionShoeListFragmentToShoeDetailFragment(shoe)
  }
}
