package test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.milad.githoob.data.api.ApiService
import com.milad.githoob.data.model.User
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class GithubServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    /**
     * Replace runBlockingTest with runTest(UnconfinedTestDispatcher())
     * @see <a href="https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md#replace-runblockingtest-with-runtestunconfinedtestdispatcher">github docs<a/>
     */
    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher)

    private lateinit var service: ApiService
    private lateinit var mockWebServer: MockWebServer

    /**
     * [MockWebServer]
     * @see <a href="https://github.com/square/okhttp/tree/master/mockwebserver">mockWebServer Docs</a>
     */
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
    fun `read sample success json file`() {
        val reader = MockResponseFileReader(this, "user-yigit.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch and check response Code 200 returned`() = testScope.runTest {
        // Assign
        val response = MockResponse().apply {
            setResponseCode(HttpURLConnection.HTTP_OK)
            setBody(MockResponseFileReader(this, "user-yigit.json").content)
        }
        mockWebServer.enqueue(response)
        // Act
        var actualResponse: Response<User>? = null
        actualResponse = service.getUser("yigit")

        // test
        assertEquals(
            "Assert Equal result.",
            response.toString().contains("200"),
            actualResponse.code().toString().contains("200")
        )
    }

    @Test
    fun `get user details`() = testScope.runTest {
        // Assign
        val response = MockResponse().apply {
            setBody(MockResponseFileReader(this, "user-yigit.json").content)
        }
        mockWebServer.enqueue(response)

        // Act
        val yigit = (service.getUser("yigit")).body()
        val request = mockWebServer.takeRequest(0, TimeUnit.MILLISECONDS)

        // test
        assertThat(request, notNullValue())
        assertThat("get user details", request?.path, `is`("/users/yigit"))

        assertThat(yigit, notNullValue())
        assertThat(yigit?.avatar_url, `is`("https://avatars3.githubusercontent.com/u/89202?v=3"))
        assertThat(yigit?.company, `is`("Google"))
        assertThat(yigit?.blog, `is`("birbit.com"))
    }

    @Test
    fun `get user repos`() = testScope.runTest {
        // Assign
        val response = MockResponse().apply {
            setBody(MockResponseFileReader(this, "repos-yigit.json").content)
        }
        mockWebServer.enqueue(response)

        //  Act
        val repos = (service.getUserRepositories("yigit", 0)).body()
        val request = mockWebServer.takeRequest(0, TimeUnit.MILLISECONDS)

        // test
        assertThat(request, notNullValue())
        assertThat(request?.path, `is`("/users/yigit/repos?sort=updated&page=0"))

        assertThat(repos, notNullValue())
        assertThat(repos?.size, `is`(2))

        val repo = repos?.get(0)
        assertThat(repo?.full_name, `is`("yigit/AckMate"))

        val owner = repo?.owner
        assertThat(owner, IsNull.notNullValue())
        assertThat(owner?.login, `is`("yigit"))
        assertThat(owner?.url, `is`("https://api.github.com/users/yigit"))

        val repo2 = repos?.get(1)
        assertThat(repo2?.full_name, `is`("yigit/android-architecture"))
    }

    @Test
    fun `get user repo contributors`() = testScope.runTest {
        // assign
        val response = MockResponse().apply {
            setBody(MockResponseFileReader(this, "contributors.`json").content)
        }
        mockWebServer.enqueue(response)

        // act

        val contributors = (service.getProjectContributors("", "foo", "bar")).body()
        val yigit = contributors?.get(0)

        // test
        assertThat(contributors?.size, `is`(3))
        assertThat(yigit?.login, `is`("yigit"))
        assertThat(
            yigit?.avatar_url,
            `is`("https://avatars3.githubusercontent.com/u/89202?v=3")
        )
        assertThat(yigit?.contributions, `is`(291))
        assertThat(contributors!![1].login, `is`("guavabot"))
        assertThat(contributors[2].login, `is`("coltin"))
    }
}

class MockResponseFileReader(obj: Any, fileName: String) {

    val content: String

    init {
        val reader =
            InputStreamReader(
                (obj.javaClass.classLoader as ClassLoader).getResourceAsStream(
                    fileName
                )
            )
        content = reader.readText()
        reader.close()
    }
}
















