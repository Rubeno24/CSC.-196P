package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumberSumScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NumberSumScreen(modifier: Modifier = Modifier) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }

    // Calculate the sum, handling empty input cases
    val sum = (num1.toIntOrNull() ?: 0) + (num2.toIntOrNull() ?: 0)

    Column(modifier = modifier.padding(24.dp)) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Enter first number") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Enter second number") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Sum: $sum",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberSumScreenPreview() {
    GreetingCardTheme {
        NumberSumScreen()
    }
}
