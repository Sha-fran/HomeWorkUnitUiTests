package com.example.homeworkunituitests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MyViewModelTest {
    @Rule
    fun getRule() = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun when_response_is_success_return_update_data() {
        val repo = Mockito.mock(Repository::class.java)
        val successfulResponce =  BitcoinResponse(Data("27000"))
        val viewModel = MyViewModel(repo)
        val eventList = mutableListOf<MyViewModel.UiState>()

        viewModel.uiState.observeForever{
            eventList.add(it)
        }

        runBlocking {
            Mockito.`when`(repo.getCurrencyByName()).thenReturn(successfulResponce)
        }

        viewModel.getData()

        Assert.assertEquals(MyViewModel.UiState.Empty, eventList[0])
        Assert.assertEquals(MyViewModel.UiState.Processing, eventList[1])

        val bitcoinState = eventList[2] as MyViewModel.UiState.Result

        Assert.assertEquals("27000", bitcoinState.bitcoinResult)
    }
}
