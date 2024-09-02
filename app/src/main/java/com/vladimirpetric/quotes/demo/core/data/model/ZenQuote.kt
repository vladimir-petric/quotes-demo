package com.vladimirpetric.quotes.demo.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZenQuote(
    @SerialName("q")
    val quote: String,
    @SerialName("a")
    val author: String
)
