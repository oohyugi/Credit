package com.yogi.credit.data

import com.yogi.credit.data.model.BaseMdl
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by oohyugi on 2019-09-06.
 * github: https://github.com/oohyugi
 */
interface Repository {

    suspend fun fetchHome(): Response<BaseMdl<List<HomeMdl>>>


    class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

        override suspend fun fetchHome(): Response<BaseMdl<List<HomeMdl>>> {
            return apiService.getHome()
        }

    }
}