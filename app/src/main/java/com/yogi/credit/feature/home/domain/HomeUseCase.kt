package com.yogi.credit.feature.home.domain

import com.yogi.credit.data.Repository
import com.yogi.credit.data.model.BaseMdl
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.remote.ResultState
import com.yogi.credit.data.utils.fetchState
import javax.inject.Inject

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
class HomeUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getHome(): ResultState<BaseMdl<List<HomeMdl>>> {
        return fetchState {
            val response = repository.fetchHome()
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error(response.message())
            }
        }
    }
}