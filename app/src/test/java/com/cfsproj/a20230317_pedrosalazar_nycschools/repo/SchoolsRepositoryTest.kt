package com.cfsproj.a20230317_pedrosalazar_nycschools.repo

import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.SchoolModel
import com.cfsproj.a20230317_pedrosalazar_nycschools.network.OpenDataAPI
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsRepositoryTest {

    private lateinit var testRepository: SchoolsRepository

    private val mockApi = mockk<OpenDataAPI>(relaxed = true)
    private val mockCallback = mockk<SchoolsRepository.SchoolsCallback>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testRepository = SchoolsRepository(mockApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `get schools when server retrieves a valid list returns success`() {
        val schools = listOf<SchoolModel>(mockk(), mockk())
        every { mockCallback.onSuccess(schools) } returns Unit
        coEvery { mockApi.highSchools() } returns listOf(mockk {
            every { dbn } returns "321"
            every { schoolName } returns "name"
            every { website }returns "www.web.com"
            every { schoolEmail }returns "schhool@email.com"
            every { overviewParagraph }returns "about us"
            every { academicopportunities1 }returns "opportunities1"
            every { academicopportunities2 }returns "opportunities2"
            every { totalStudents }returns "1000"
        })
        coEvery { mockApi.satScores() } returns listOf(mockk {
            every { dbn } returns "123"
        })

        val job = testScope.launch {
            testRepository.getSchoolModel(mockCallback)
        }

        verify { mockCallback.onSuccess(any()) }

        job.cancel()
    }
}