package com.gojek_assignment_android.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.gojek_assignment_android.interfaces.ResponseListener
import com.gojek_assignment_android.model.TrendingRepositories_model
import com.gojek_assignment_android.viewmodel.TrendingRepositories_Viewmodel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepositoryTest : ResponseListener {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var mRepository: Repository
    @Mock
    lateinit var mResponseListener: ResponseListener
    @Mock
    private lateinit var mTrendingRepositories_Viewmodel: TrendingRepositories_Viewmodel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(Repository::javaClass)
        MockitoAnnotations.initMocks(this)
        mResponseListener = this


    }


    @Test
    fun getTrendingrepoLiveData() {


        val mTrendingrepoLiveData = MutableLiveData<List<TrendingRepositories_model>>()


        Mockito.`when`(mRepository.getTrendingrepoLiveData(mResponseListener)).thenAnswer {

            mTrendingrepoLiveData.postValue(mRepository.getTrendingrepoLiveData(mResponseListener)!!.value)

            return@thenAnswer mTrendingrepoLiveData
        }


    }

    override fun onError(message: String) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        System.out.println(message)
    }
}