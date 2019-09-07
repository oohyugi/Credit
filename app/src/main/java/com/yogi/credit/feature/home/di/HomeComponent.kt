package com.yogi.credit.feature.home.di

import com.yogi.credit.feature.home.ui.HomeFragment
import dagger.Component

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */

@HomeScope
@Component(
    modules = [
        HomeModule::class,
        HomeViewModelModule::class
    ]
)
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}