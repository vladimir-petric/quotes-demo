package com.vladimirpetric.quotes.demo.list.data.api

import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote

interface QuotesApi {
    suspend fun randomQuotes(): List<ZenQuote>
}