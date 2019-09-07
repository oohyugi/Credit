package com.yogi.credit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yogi.credit.base.UiState
import com.yogi.credit.data.model.BaseMdl
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.model.ItemsMdl
import com.yogi.credit.data.remote.ResultState
import com.yogi.credit.feature.home.domain.HomeUseCase
import com.yogi.credit.feature.home.ui.HomeViewModel
import com.yogi.credit.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var result: Observer<UiState<List<HomeMdl>>>
    @Mock
    lateinit var useCase: HomeUseCase

    private lateinit var viewModel: HomeViewModel
    private val appDispatchers = AppDispatchers(Dispatchers.IO, Dispatchers.Main)

    private val listHomes = listOf(
        HomeMdl(
            "sectionTitle",
            "section",
            listOf(ItemsMdl())
        )
    )

    private val homesData = BaseMdl(listHomes)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(appDispatchers.main)
        viewModel = HomeViewModel(useCase, appDispatchers)
        viewModel.state.observeForever(result)

    }


    @Test
    fun responseHomeData() = runBlocking {
        val returnValue = ResultState.Success(homesData)
        `when`(useCase.getHome()).thenReturn(returnValue)
        viewModel.loadHome()


        //then
//        verify(result).onChanged(UiState.ShowLoading)
        verify(result).onChanged(UiState.Success(homesData.data))
        clearInvocations(useCase, result)

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}