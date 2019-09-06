package com.yogi.credit.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.credit.base.UiState
import com.yogi.credit.data.Repositiory
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.remote.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: Repositiory.RepositoryImpl) :
    ViewModel() {

    private val _state = MutableLiveData<UiState<List<HomeMdl>>>()
    val state: LiveData<UiState<List<HomeMdl>>> = _state
    private var mListHome = mutableListOf<HomeMdl>()


    init {
        loadHome()

    }

    private fun loadHome() {

        _state.value = UiState.ShowLoading
        viewModelScope.launch {
            val request = repo.fecthHome()
            _state.value = UiState.StopLoading
            withContext(Dispatchers.Main) {

                when (request) {
                    is ResultState.Success -> {
                        request.data?.let {


                            it.data.withIndex().reversed().forEach { value ->
                                mListHome.add(value.value)
                            }


                            _state.value =UiState.Success(mListHome)
                        }
                    }
                    is ResultState.Error -> {
                        _state.postValue( UiState.ShowError(request.errorMessage))


                    }
                }
            }
        }
    }

    fun refreshData() {
        loadHome()
    }


}
