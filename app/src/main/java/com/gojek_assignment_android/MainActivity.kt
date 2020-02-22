package com.gojek_assignment_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mShimmerViewContainer: ShimmerFrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        //findid the shimmer View from xml
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)
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
