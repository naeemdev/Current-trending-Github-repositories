package com.gojek_assignment_android

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mShimmerViewContainer: ShimmerFrameLayout? = null
    var layout_nointernet: LinearLayout? = null
    var layout_shimmer: LinearLayout? = null
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

}
