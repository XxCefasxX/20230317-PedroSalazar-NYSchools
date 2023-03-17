package com.cfsproj.a20230317_pedrosalazar_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cfsproj.a20230317_pedrosalazar_nycschools.repo.SchoolsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.Every
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.http.GET
import java.time.Instant

class SchoolViewModelTest {


    private lateinit var vm: SchoolViewModel
    private val mockRepository = mockk<SchoolsRepository>()

    val testDispatcher = UnconfinedTestDispatcher()
    @get:Rule  val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun `get schools when repository returns an error returns state`() {

    }
    @After
    fun tearDown() {
    }
}