package com.mfrancetic.bookstore.utils

import android.app.Activity
import android.content.Context

class SharedPreferencesHelper {

    companion object {

        private const val SHARED_PREFERENCES_USER_LOGGED_IN_KEY = "userLoggedIn"

        fun addLoginStatusToSharedPreferences(activity: Activity, isLoggedIn: Boolean) {
            val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return
            sharedPreferences.edit()
                    .putBoolean(SHARED_PREFERENCES_USER_LOGGED_IN_KEY, isLoggedIn)
                    .apply()
        }

        fun getLoginStatusFromSharedPreferences(activity: Activity): Boolean {
            val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(SHARED_PREFERENCES_USER_LOGGED_IN_KEY, false)
        }
    }
}