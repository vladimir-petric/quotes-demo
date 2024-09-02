package com.vladimirpetric.quotes.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vladimirpetric.quotes.demo.list.presentation.QuotesListActivity
import com.vladimirpetric.quotes.demo.ui.theme.QuotesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Greeting(
                            name = "handsome"
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        InputTextField(
                            initialValue = "",
                            onValueChange = {},
                            label = "Enter your name"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StartListActivityButton(
    modifier: Modifier = Modifier,
    extra: String = "",
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { /* Handle the result if needed */ }

    Text(text = "Click the button to start the list activity")
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = {
        val intent = Intent(context, QuotesListActivity::class.java).apply {
            putExtra(QuotesListActivity.EXTRA, extra)
        }
        // Start the activity using the launcher
        launcher.launch(intent)
    }) {
        Text(
            text = "Start QuotesListActivity",
            modifier = modifier
        )
    }
}

@Composable
fun InputTextField(
    initialValue: String,
    onValueChange: () -> Unit,
    label: String
) {
    var text by remember { mutableStateOf(initialValue) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange()
            Log.v("InputTextField", "onValueChange($it)")
        },
        label = { Text(label) },
        readOnly = false
    )
    Spacer(modifier = Modifier.height(20.dp))
    StartListActivityButton(
        modifier = Modifier.padding(4.dp),
        extra = text,
    )
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