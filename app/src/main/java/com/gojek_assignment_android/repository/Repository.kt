package com.gojek_assignment_android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gojek_assignment_android.interfaces.ResponseListener
import com.gojek_assignment_android.model.TrendingRepositories_model
import com.gojek_assignment_android.retrofit.ApiClient
import com.gojek_assignment_android.retrofit.ApiService_Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun getTrendingrepoLiveData(listener: ResponseListener): MutableLiveData<List<TrendingRepositories_model>> {
        val mTrendingrepoLiveData = MutableLiveData<List<TrendingRepositories_model>>()
        ///ini Retrofit Class

        val apiInterface = ApiClient.apiClient!!.create(ApiService_Interface::class.java)

        val call = apiInterface.getrepositoriesList()

        call!!.enqueue(object : Callback<List<TrendingRepositories_model>> {

            override fun onResponse(
                call: Call<List<TrendingRepositories_model>?>,
                response: Response<List<TrendingRepositories_model>?>
            ) {

                if (response.isSuccessful) {
                    mTrendingrepoLiveData.postValue(response.body())


                } else {
                    listener.onError(response.errorBody().toString())
                    Log.e("error", response.errorBody().toString())
                }
                //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<List<TrendingRepositories_model>?>, t: Throwable) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("error", t.message.toString())
                listener.onError(t.message.toString())
            }
        })


        return mTrendingrepoLiveData
    }


}