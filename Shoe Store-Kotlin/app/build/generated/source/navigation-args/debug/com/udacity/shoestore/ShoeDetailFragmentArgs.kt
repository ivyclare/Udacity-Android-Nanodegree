package com.udacity.shoestore

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.udacity.shoestore.models.Shoe
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

data class ShoeDetailFragmentArgs(
  val shoe: Shoe
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  fun toBundle(): Bundle {
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

  companion object {
    @JvmStatic
    fun fromBundle(bundle: Bundle): ShoeDetailFragmentArgs {
      bundle.setClassLoader(ShoeDetailFragmentArgs::class.java.classLoader)
      val __shoe : Shoe?
      if (bundle.containsKey("shoe")) {
        if (Parcelable::class.java.isAssignableFrom(Shoe::class.java) ||
            Serializable::class.java.isAssignableFrom(Shoe::class.java)) {
          __shoe = bundle.get("shoe") as Shoe?
        } else {
          throw UnsupportedOperationException(Shoe::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__shoe == null) {
          throw IllegalArgumentException("Argument \"shoe\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"shoe\" is missing and does not have an android:defaultValue")
      }
      return ShoeDetailFragmentArgs(__shoe)
    }
  }
}
