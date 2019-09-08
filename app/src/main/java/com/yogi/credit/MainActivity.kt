package com.yogi.credit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yogi.credit.feature.home.ui.HomeFragment
import com.yogi.credit.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment.newInstance(), R.id.container, "home")
        }
    }
}
