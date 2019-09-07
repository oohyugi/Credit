package com.yogi.credit.base

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
sealed class UiState<out R> {
    object ShowLoading:UiState<Nothing>()
    data class ShowError(val errorMessage:String?):UiState<Nothing>()
    data class Success<T>(val data :T):UiState<T>()
}