package com.yogi.credit

import com.yogi.credit.data.Repository
import com.yogi.credit.data.model.BaseMdl
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.model.ItemsMdl
import com.yogi.credit.data.remote.ResultState
import com.yogi.credit.feature.home.domain.HomeUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * Created by oohyugi on 2019-09-06.
 * github: https://github.com/oohyugi
 */
class HomeUseCaseTest {

    private var repository = mock(Repository::class.java)
    private lateinit var homeUseCase: HomeUseCase
    var response: Response<BaseMdl<List<HomeMdl>>>? = null

    private val homes = listOf(
        HomeMdl(
            "sectionTitle",
            "section",
            listOf(ItemsMdl())
        )
    )

    @Before
    fun setup() {
        homeUseCase = HomeUseCase(repository)
    }

    @Test
    fun `should return home success`() {
        val actual = ResultState.Success(BaseMdl(homes))

        val result = runBlocking {
            `when`(repository.fetchHome())
                .thenReturn(Response.success(BaseMdl(homes)))

            homeUseCase.getHome()
        }
        assertEquals(result, actual)
    }

    @Test
    fun `should return error`() {
        val actual = ResultState.Error("")
        val result = runBlocking {
            `when`(repository.fetchHome())
                .thenReturn(
                    Response.error(
                        401,
                        ResponseBody.create(MediaType.parse("application/json"), "")
                    )
                )
            homeUseCase.getHome()
        }

        // probably has different error message, so you can check by type of java class
        assert(result.javaClass === actual.javaClass)
    }
}
