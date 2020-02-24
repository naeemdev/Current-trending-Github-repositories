package com.gojek_assignment_android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.gojek_assignment_android.CustomAdatper.TrendingRepo_CustomAdapter
import com.gojek_assignment_android.interfaces.ResponseListener
import com.gojek_assignment_android.model.TrendingRepositories_model
import com.gojek_assignment_android.sharedprefer.SharedPreferenceHelper
import com.gojek_assignment_android.viewmodel.TrendingRepositories_Viewmodel
import com.hendraanggrian.recyclerview.widget.ExpandableRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit
import com.gojek_assignment_android.Utils.isConnectedToNetwork as isConnectedToNetwork1

class MainActivity : AppCompatActivity(), ResponseListener {

    var mShimmerViewContainer: ShimmerFrameLayout? = null
    var layout_nointernet: RelativeLayout? = null
    var layout_shimmer: LinearLayout? = null
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    private var mTrendingRepo_CustomAdapter: TrendingRepo_CustomAdapter? = null
    private lateinit var mTrendingRepositories_Viewmodel: TrendingRepositories_Viewmodel
    private var recyclerView: ExpandableRecyclerView? = null

    var mSharedPreferenceHelper: SharedPreferenceHelper? = null


    internal var mTrendingrepoLiveData: MutableList<TrendingRepositories_model> =
        ArrayList<TrendingRepositories_model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        mTrendingRepositories_Viewmodel =
            ViewModelProvider(this).get(TrendingRepositories_Viewmodel::class.java)

        mSharedPreferenceHelper = SharedPreferenceHelper(this)
        //find by id  shimmer View from xml
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)

        recyclerView = findViewById(R.id.recyclerview_trending)


        /*Find by id  No internet  layout for visibillty*/
        layout_nointernet = findViewById(R.id.layout_nointernet)

        /*Find by id shimmer  layout for visibillty*/
        layout_shimmer = findViewById(R.id.layout_shimmer)

        mSwipeRefreshLayout = findViewById(R.id.mSwipeRefreshLayout)

        checkvalidation()
        mSwipeRefreshLayout!!.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {


            checkvalidation()

            mSwipeRefreshLayout!!.isRefreshing = false


        })
    }


    override fun onResume() {
        super.onResume()
        //start  Shimmer Animation
        mShimmerViewContainer!!.startShimmerAnimation()
    }

    override fun onPause() {
        //Stop  Shimmer Animation
        mShimmerViewContainer!!.stopShimmerAnimation()
        super.onPause()
    }


    private fun checkvalidation() {
        /*
           check is internet connected or not if its connected than call to API
          else   show no internet connection layout
          */
        if (isConnectedToNetwork1()) {
            /// call api for load data
            mShimmerViewContainer!!.startShimmerAnimation()
            layout_shimmer!!.visibility = View.VISIBLE
            layout_nointernet!!.visibility = View.GONE
            mTrendingrepoLiveData.clear()

            mTrendingrepoLiveData =
                mSharedPreferenceHelper!!.getTrendingRepo() as MutableList<TrendingRepositories_model>

            if (mSharedPreferenceHelper!!.expired > System.currentTimeMillis()) {

                mShimmerViewContainer!!.stopShimmerAnimation()
                layout_shimmer!!.visibility = View.GONE

                setdatatorecyclerView()
            } else {

                calltoapi()
            }

        } else {
            layout_shimmer!!.visibility = View.GONE
            layout_nointernet!!.visibility = View.VISIBLE
        }
    }


    fun calltoapi() {
        mSharedPreferenceHelper!!.clearPrefs()
        mTrendingRepositories_Viewmodel.getTrendingRepository(this)!!.observe(this,
            Observer<List<TrendingRepositories_model>> { mTrendingRepositories_model ->
                Log.e("sizea_rray", mTrendingRepositories_model.size.toString())
                mShimmerViewContainer!!.stopShimmerAnimation()
                layout_shimmer!!.visibility = View.GONE
                mTrendingrepoLiveData.addAll(mTrendingRepositories_model)
                mSharedPreferenceHelper!!.setTrendingRepoData(mTrendingRepositories_model)
                mSharedPreferenceHelper!!.expired =
                    System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(120)
                setdatatorecyclerView()
            })
    }
    fun setdatatorecyclerView() {

        if (mTrendingrepoLiveData.size > 0) {
            layout_shimmer!!.visibility = View.GONE
            layout_nointernet!!.visibility = View.GONE
            val layoutManager = LinearLayoutManager(this)
            recyclerView!!.layoutManager = layoutManager
            mTrendingRepo_CustomAdapter =
                TrendingRepo_CustomAdapter(layoutManager, this, mTrendingrepoLiveData)
            //set the CustomAdapter
            recyclerView!!.setAdapter(mTrendingRepo_CustomAdapter)
        } else {
            layout_shimmer!!.visibility = View.GONE
            layout_nointernet!!.visibility = View.VISIBLE
        }

    }
    ///click listener for Retry button
    fun onRettyclick(view: View) {
        //call to api when retry click
        calltoapi()

    }

    override fun onError(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        layout_shimmer!!.visibility = View.GONE
        layout_nointernet!!.visibility = View.VISIBLE

    }
}
