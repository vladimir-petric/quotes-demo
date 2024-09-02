package com.vladimirpetric.quotes.demo.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimirpetric.quotes.demo.list.data.repository.QuotesListRepositoryImpl
import com.vladimirpetric.quotes.demo.list.data.api.QuotesApiImpl
import com.vladimirpetric.quotes.demo.list.domain.QuotesListRepository
import com.vladimirpetric.quotes.demo.list.presentation.model.QuotesListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuotesListViewModel : ViewModel() {

    private val repository: QuotesListRepository by lazy {
        QuotesListRepositoryImpl(QuotesApiImpl())
    }

    private val _uiState = MutableStateFlow<QuotesListUiState>(QuotesListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetch() {
        _uiState.value = QuotesListUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.randomQuotes()
            result.onSuccess {
                _uiState.value =
                    QuotesListUiState.Success(it)
            }.onFailure {
                Log.e("QuotesListViewModel", "Failure: $it")
                _uiState.value = QuotesListUiState.GeneralError
            }
        }
    }
}