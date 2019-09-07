package com.yogi.credit.feature.home.di

import com.yogi.credit.data.Repository
import com.yogi.credit.data.di.DataModule
import com.yogi.credit.data.di.DataScope
import com.yogi.credit.data.remote.ApiService
import com.yogi.credit.feature.home.domain.HomeUseCase
import com.yogi.credit.utils.AppDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */

@Module(includes = [DataModule::class])
class HomeModule {

    @HomeScope
    @Provides
    fun provideRepository(@DataScope apiService: ApiService): Repository {
        return Repository.RepositoryImpl(apiService)
    }

    @HomeScope
    @Provides
    fun provideHomeUseCase(repository: Repository): HomeUseCase {
        return HomeUseCase(repository)
    }
    @HomeScope @Provides
    fun provideAppDispatcher(): AppDispatchers = AppDispatchers(Dispatchers.Main,Dispatchers.IO)

}