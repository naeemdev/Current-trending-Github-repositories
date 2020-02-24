package com.gojek_assignment_android.sharedprefer

import android.content.Context
import android.content.SharedPreferences
import com.gojek_assignment_android.model.TrendingRepositories_model
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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


    fun setTrendingRepoData(model: List<TrendingRepositories_model>) {
        editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(model)
        editor!!.putString(REPO_DATA_KEY, json)
        editor!!.apply()
        editor!!.commit()
    }


    fun getTrendingRepo(): List<TrendingRepositories_model>? {
        val gson = Gson()
        val models: List<TrendingRepositories_model> = ArrayList<TrendingRepositories_model>()
        val json = preferences.getString(REPO_DATA_KEY, "")
        return if (json == "") {
            models
        } else {
            val listType = object :
                TypeToken<java.util.ArrayList<TrendingRepositories_model?>?>() {}.type
            gson.fromJson<List<TrendingRepositories_model>>(json, listType)
        }
    }





    init {
        preferences =
            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }




}