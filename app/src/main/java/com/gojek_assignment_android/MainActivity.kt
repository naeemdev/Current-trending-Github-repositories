package com.gojek_assignment_android

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import com.gojek_assignment_android.Utils.isConnectedToNetwork as isConnectedToNetwork1

class MainActivity : AppCompatActivity() {

    var mShimmerViewContainer: ShimmerFrameLayout? = null
    var layout_nointernet: LinearLayout? = null
    var layout_shimmer: LinearLayout? = null
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        //find by id  shimmer View from xml
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)

        /*Find by id  No internet  layout for visibillty*/
        layout_nointernet = findViewById(R.id.layout_nointernet)

        /*Find by id shimmer  layout for visibillty*/
        layout_shimmer = findViewById(R.id.layout_shimmer)
        mSwipeRefreshLayout = findViewById(R.id.mSwipeRefreshLayout)

        mSwipeRefreshLayout!!.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {


            calltoapi()

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


    private fun calltoapi() {
        /*
           check is internet connected or not if its connected than call to API
          else   show no internet connection layout
          */
        if (isConnectedToNetwork1()) {
            /// call api for load data
            layout_shimmer!!.visibility = View.VISIBLE
        } else {
            layout_shimmer!!.visibility = View.GONE
            layout_nointernet!!.visibility = View.VISIBLE
        }
    }
}
