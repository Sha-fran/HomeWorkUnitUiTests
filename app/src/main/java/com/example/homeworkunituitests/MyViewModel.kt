package com.example.homeworkunituitests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val repo:Repository):ViewModel() {
    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState:LiveData<UiState> = _uiState

    fun getData() {
        _uiState.value = UiState.Processing

        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                try {
                    val bitcoinResponse = repo.getCurrencyByName("bitcoin")

                    if (bitcoinResponse.isSuccessful) {
                        val data = bitcoinResponse.body()?.data
                        _uiState.postValue(UiState.Result("${data?.id} ${data?.rateUsd}\n"))
                    } else {
                        _uiState.postValue(UiState.Error("Error code ${bitcoinResponse.code()}"))
                    }
                } catch (e: Exception) {
                    _uiState.postValue(e.localizedMessage?.let { UiState.Error(it) })
                }
            }
        }
    }

    sealed class UiState {
        object Empty:UiState()
        object Processing:UiState()
        class Result(val title:String):UiState()
        class Error(val description:String):UiState()
    }
}
