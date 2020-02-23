package com.gojek_assignment_android.retrofit


import com.gojek_assignment_android.model.TrendingRepositories_model
import retrofit2.Call
import retrofit2.http.GET


interface ApiService_Interface {


    @GET("repositories")
    fun getrepositoriesList(): Call<List<TrendingRepositories_model>>?


}