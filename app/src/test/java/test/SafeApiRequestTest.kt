package test

import com.milad.githoob.data.api.ApiService
import com.milad.githoob.data.model.AccessToken
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.utils.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class SafeApiRequestTest : SafeApiRequest() {
    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher)

    private lateinit var service: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun stopService() {
        Dispatchers.resetMain()
        mockWebServer.shutdown()
    }

    @Test
    fun `first test for check api request`() = testScope.runTest {

        val response = MockResponse().apply {
            setBody(MockResponseFileReader(this, "repos-yigit.json").content)
        }
        mockWebServer.enqueue(response)

        val ss = apiRequest {
            response()
        }

        MatcherAssert.assertThat(ss, CoreMatchers.notNullValue())
    }

    private suspend fun response(): Response<ArrayList<Repo>> {
        return service.getUserRepositories("", 0)
    }
}