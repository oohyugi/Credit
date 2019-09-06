package com.yogi.credit.feature.di

import com.yogi.credit.data.Repositiory
import com.yogi.credit.data.di.DataModule
import com.yogi.credit.data.di.DataScope
import com.yogi.credit.data.remote.ApiService
import com.yogi.credit.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */

@Module(includes = [DataModule::class])
class HomeModule {

    @HomeScope @Provides
    fun provideRemoteDataSource(@DataScope service: ApiService): RemoteDataSource{
        return RemoteDataSource(service)
    }

    @HomeScope @Provides
    fun provideRepository(@DataScope dataSource: RemoteDataSource): Repositiory{
        return Repositiory.RepositoryImpl(dataSource)
    }
}