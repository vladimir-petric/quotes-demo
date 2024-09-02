package com.vladimirpetric.quotes.demo.list.data

import androidx.datastore.core.Serializer
import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ZenQuoteListSerializer : Serializer<List<ZenQuote>> {

    override val defaultValue: List<ZenQuote> = emptyList()

    override suspend fun readFrom(input: InputStream): List<ZenQuote> {
        return try {
            Json.decodeFromString(String(input.readBytes()))
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: List<ZenQuote>, output: OutputStream) {
        withContext(Dispatchers.IO) {
            val serializer = ListSerializer(ZenQuote.serializer())
            output.write(Json.encodeToString(serializer, t).toByteArray())
        }
    }
}