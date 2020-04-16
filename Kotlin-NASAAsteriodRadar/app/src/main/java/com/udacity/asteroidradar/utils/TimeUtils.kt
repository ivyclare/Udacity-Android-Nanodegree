package com.udacity.asteroidradar.utils

import com.udacity.asteroidradar.Constants
import java.text.SimpleDateFormat
import java.util.*



class TimeUtils {

    companion object {

        fun getTodayDate() : String {
            val calendar = Calendar.getInstance()
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            return dateFormat.format(currentTime)
        }
    }

}