package com.yogi.credit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogi.credit.feature.ui.HomeFragment
import com.yogi.credit.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null){
            replaceFragment(HomeFragment.newInstance(),R.id.container,"home")
        }


    }
}
