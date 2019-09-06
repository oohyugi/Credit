package com.yogi.credit.data.di

import com.yogi.credit.data.remote.ApiService
import dagger.Module
import dagger.Provides

/**
 * Created by oohyugi on 2019-09-06.
 * github: https://github.com/oohyugi
 */
@Module class DataModule{
    @Provides @DataScope
    fun provideService(): ApiService {
        return ApiService.retrofitClient().create(ApiService::class.java)
    }
}