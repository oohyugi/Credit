package com.yogi.credit.data.remote

import com.yogi.credit.BuildConfig
import javax.inject.Inject

/**
 * Created by oohyugi on 2019-09-06.
 * github: https://github.com/oohyugi
 */
class RemoteDataSource @Inject constructor(private val mApiService: ApiService) {

    suspend fun fetchHome() = mApiService.getHome()

}