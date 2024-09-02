package com.vladimirpetric.quotes.demo.list.data.repository

import android.util.Log
import com.vladimirpetric.quotes.demo.list.data.api.QuotesApi
import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote
import com.vladimirpetric.quotes.demo.list.domain.QuotesListRepository

class QuotesListRepositoryImpl(
    private val quotesApi: QuotesApi
) : QuotesListRepository {
    override suspend fun randomQuotes(): Result<List<ZenQuote>> {
        return try {
            Result.success(
                quotesApi.randomQuotes()
            )
        } catch (throwable: Throwable) {
            Log.e("QuotesListRepository", "Caught a throwable", throwable)
            Result.failure(throwable)
        }
    }
}