package com.example.homeworkunituitests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val repo:Repository):ViewModel() {
    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState:LiveData<UiState> = _uiState

    fun getData() {
        _uiState.value = UiState.Processing

        viewModelScope.launch(Dispatchers.IO) {
            val bitcoinResponse = repo.getCurrencyByName()

            if (bitcoinResponse.data != null) {
                val data = bitcoinResponse.data
                _uiState.postValue(UiState.Result(data.rateUsd))
            } else {
                _uiState.postValue(UiState.Error("Error"))
            }
        }
    }

    sealed class UiState {
        object Empty:UiState()
        object Processing:UiState()
        class Result(val bitcoinResult:String):UiState()
        class Error(val description:String):UiState()
    }
}
