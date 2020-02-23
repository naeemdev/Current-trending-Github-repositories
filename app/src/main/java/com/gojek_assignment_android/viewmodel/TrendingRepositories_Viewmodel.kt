package com.gojek_assignment_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gojek_assignment_android.interfaces.ResponseListener
import com.gojek_assignment_android.model.TrendingRepositories_model
import com.gojek_assignment_android.repository.Repository

class TrendingRepositories_Viewmodel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository
    fun getTrendingRepository(listener: ResponseListener): MutableLiveData<List<TrendingRepositories_model>>? {
        return mRepository.getTrendingrepoLiveData(listener)
    }

    init {
        mRepository = Repository()
    }

}