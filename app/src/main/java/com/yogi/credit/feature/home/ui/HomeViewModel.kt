package com.yogi.credit.feature.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.credit.base.UiState
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.remote.ResultState
import com.yogi.credit.feature.home.domain.HomeUseCase
import com.yogi.credit.utils.AppDispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase,
    private val dispatcher: AppDispatchers
) :
    ViewModel() {

    private val _state = MutableLiveData<UiState<List<HomeMdl>>>()
    val state: LiveData<UiState<List<HomeMdl>>> = _state
    private var mListHome = mutableListOf<HomeMdl>()


    init {
        loadHome()

    }

    fun loadHome() {

        _state.value = UiState.ShowLoading
        viewModelScope.launch {
            val request = withContext(dispatcher.io) {

                useCase.getHome()


            }
            when (request) {
                is ResultState.Success -> {
                    request.data?.let {


                        it.data.withIndex().reversed().forEach { value ->
                            mListHome.add(value.value)
                        }


                        _state.value = UiState.Success(mListHome)
                    }
                }
                is ResultState.Error -> {
                    _state.postValue(UiState.ShowError(request.errorMessage))


                }
            }


        }
    }

    fun refreshData() {
        loadHome()
    }


}
