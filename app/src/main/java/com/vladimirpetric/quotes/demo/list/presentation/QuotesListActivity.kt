package com.vladimirpetric.quotes.demo.list.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladimirpetric.quotes.demo.core.data.model.ZenQuote
import com.vladimirpetric.quotes.demo.list.presentation.model.QuotesListUiState
import com.vladimirpetric.quotes.demo.list.presentation.ui.theme.QuotesDemoTheme

class QuotesListActivity : ComponentActivity() {

    private val quotesListViewModel: QuotesListViewModel by viewModels {
        QuotesListViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Spacer(modifier = Modifier.height(24.dp))
                        Greeting(
                            name = intent.getStringExtra(EXTRA) ?: "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        QuotesList(quotesListViewModel)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        quotesListViewModel.fetch()
    }

    companion object {
        const val EXTRA = "EXTRA"

        private class QuotesListViewModelFactory : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(QuotesListViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return QuotesListViewModel() as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

@Composable
fun QuotesList(quotesListViewModel: QuotesListViewModel) {
    val uiState by quotesListViewModel.uiState.collectAsState()
    when (uiState) {
        is QuotesListUiState.Success -> {
            val quotes = (uiState as QuotesListUiState.Success).quotes
            Log.d("QuotesListActivity", "Quotes: $quotes")
            // Display the list of quotes
            ZenQuoteList(quotes)
        }
        QuotesListUiState.Loading -> { /* TODO */ }
        QuotesListUiState.GeneralError -> {
            Log.e("QuotesListActivity", "Received error: General error")
        }
        QuotesListUiState.NetworkError -> {
            Log.e("QuotesListActivity", "Received error: Network error")
        }
    }
}

@Composable
fun ZenQuoteList(quotes: List<ZenQuote>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(quotes) { quote ->
            ZenQuoteCard(quote)
        }
    }
}

@Composable
fun ZenQuoteCard(quote: ZenQuote) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(quote.quote)
            Spacer(modifier = Modifier.height(8.dp))
            Text("- ${quote.author}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuotesDemoTheme {
        Greeting("Android")
    }
}