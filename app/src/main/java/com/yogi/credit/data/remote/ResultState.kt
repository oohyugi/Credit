package com.yogi.credit.data.remote

/**
 * Created by oohyugi on 2019-09-06.
 * github: https://github.com/oohyugi
 */
sealed class ResultState<out T : Any?> {
    data class Success<out T : Any?>(val data: T?) : ResultState<T>()
    data class Error(val errorMessage: String?) : ResultState<Nothing>()
}