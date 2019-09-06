package com.yogi.credit.data

import com.yogi.credit.data.model.BaseMdl
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.remote.ApiService
import com.yogi.credit.data.remote.RemoteDataSource
import com.yogi.credit.data.remote.ResultState
import com.yogi.credit.data.utils.fetchState
import javax.inject.Inject

/**
 * Created by oohyugi on 2019-09-06.
 * github: https://github.com/oohyugi
 */
interface Repositiory {

    suspend fun fecthHome(): ResultState<BaseMdl<List<HomeMdl>>>


    class RepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) : Repositiory {

        override suspend fun fecthHome(): ResultState<BaseMdl<List<HomeMdl>>> {
            return fetchState {
                val mRequest = dataSource.fetchHome()
                if (mRequest.isSuccessful) {
                    ResultState.Success(mRequest.body())
                } else {
                    ResultState.Error(mRequest.message())
                }

            }
        }

    }
}