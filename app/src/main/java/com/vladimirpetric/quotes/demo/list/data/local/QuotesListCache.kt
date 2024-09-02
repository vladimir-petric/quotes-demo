package com.vladimirpetric.quotes.demo.list.data.local

import android.content.Context
import com.vladimirpetric.quotes.demo.list.data.ZenQuoteListSerializer

class QuotesListCache private constructor(context: Context) {

    companion object {
        fun getInstance(context: Context) = lazy {
            QuotesListCache(context)
        }
    }

    val dataStore = androidx.datastore.dataStore(
        fileName = "quotes.json",
        serializer = ZenQuoteListSerializer
    )
}