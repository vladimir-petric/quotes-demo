package com.vladimirpetric.quotes.demo.list.data.api

import com.vladimirpetric.quotes.demo.core.data.HttpClientProvider
import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote
import io.ktor.client.call.body
import io.ktor.client.request.get

class QuotesApiImpl : QuotesApi {
    override suspend fun randomQuotes(): List<ZenQuote> {
        return HttpClientProvider.httpClient
            .get("https://zenquotes.io/api/quotes")
            .body()
    }
}