package com.gojek_assignment_android.sharedprefer

import android.content.Context
import android.content.SharedPreferences
 
import java.util.concurrent.TimeUnit


class SharedPreferenceHelper(context: Context) {

    private val preferences: SharedPreferences
    private var editor: SharedPreferences.Editor? = null

    private val REPO_DATA_KEY = "reporp_data"
    private val EXPIRE_DATA_KEY = "ExpiredDat"


    //for expired
    var expired: Long
        get() = preferences.getLong(
            EXPIRE_DATA_KEY,
            System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(0)
        )
        set(expiredda) {
            editor = preferences.edit()
            editor!!.putLong(EXPIRE_DATA_KEY, expiredda!!)
            editor!!.apply()
            editor!!.commit()
        }


    fun clearPrefs() {
        preferences.edit().clear().commit()
    }


    init {
        preferences =
            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }




}