package com.yogi.credit

import com.yogi.credit.data.model.BaseMdl
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.data.model.ItemsMdl
import com.yogi.credit.data.remote.ApiService
import com.yogi.credit.data.remote.RemoteDataSource
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
class DataSourceTest {

    private var mApiService = mock(ApiService::class.java)
    private lateinit var remoteDataSource: RemoteDataSource

    private val homes = listOf(
        HomeMdl(
            "sectionTitle",
            "section",
            listOf(ItemsMdl())
        )
    )

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(mApiService)

    }

    @Test
    fun `should get home success`() = runBlocking {
        `when`(mApiService.getHome()).thenReturn(
            Response.success(BaseMdl(homes))
        )

        val dataSource = remoteDataSource.fetchHome()
        assertEquals(dataSource.body(), BaseMdl(homes))
    }

    @Test
    fun `should get error`() = runBlocking {
        `when`(mApiService.getHome()).thenReturn(
            Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
        )

        val dataSource = remoteDataSource.fetchHome()
        assertEquals(dataSource.body(), null)
    }
}