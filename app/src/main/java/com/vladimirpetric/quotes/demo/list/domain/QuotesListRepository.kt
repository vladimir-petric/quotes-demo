package com.vladimirpetric.quotes.demo.list.domain

import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote

interface QuotesListRepository {
    suspend fun randomQuotes(): Result<List<ZenQuote>>
}