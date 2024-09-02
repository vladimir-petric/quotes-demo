package com.vladimirpetric.quotes.demo.list.presentation.model

import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote

@Suppress("ConvertObjectToDataObject")
sealed class QuotesListUiState {
    object Loading : QuotesListUiState()
    data class Success(val quotes: List<ZenQuote>) : QuotesListUiState()
    object NetworkError : QuotesListUiState()
    object GeneralError : QuotesListUiState()
}
