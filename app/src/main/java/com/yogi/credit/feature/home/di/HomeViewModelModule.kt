package com.yogi.credit.feature.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yogi.credit.feature.home.ui.HomeViewModel
import com.yogi.credit.utils.ViewModelFactory
import com.yogi.credit.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */

@Module
abstract class HomeViewModelModule {

    @HomeScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
