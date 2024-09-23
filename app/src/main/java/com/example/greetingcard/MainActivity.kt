package com.example.greetingcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    var sum by remember { mutableStateOf(0) }
    var isNum1Valid by remember { mutableStateOf(true) } // State to track if num1 is valid
    var isNum2Valid by remember { mutableStateOf(true) } // State to track if num2 is valid
    val context = LocalContext.current // For showing the toast


    Column(modifier = modifier.padding(24.dp)) {
        // Input field for first number with validation
        TextField(
            value = num1,
            onValueChange = { input ->
                try {
                    num1 = input
                    input.toInt() // Attempt to parse to an integer
                    isNum1Valid = true // Valid number
                } catch (e: NumberFormatException) {
                    isNum1Valid = false // Invalid number
                }
            },
            label = { Text("Enter first number") },
            isError = !isNum1Valid, // Highlight the field in red if invalid
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (!isNum1Valid) {
            Text(
                text = "Please enter a valid number",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Input field for second number with validation
        TextField(
            value = num2,
            onValueChange = { input ->
                try {
                    num2 = input
                    input.toInt() // Attempt to parse to an integer
                    isNum2Valid = true // Valid number
                } catch (e: NumberFormatException) {
                    isNum2Valid = false // Invalid number
                }
            },
            label = { Text("Enter second number") },
            isError = !isNum2Valid, // Highlight the field in red if invalid
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (!isNum2Valid) {
            Text(
                text = "Please enter a valid number",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Button to calculate the sum
        Button(
            onClick = {
                if (num1.isBlank() || num2.isBlank()) {
                    Toast.makeText(context, "Please enter a number that isn't blank", Toast.LENGTH_SHORT).show()

                }
                if (isNum1Valid && isNum2Valid) {
                    sum = (num1.toIntOrNull() ?: 0) + (num2.toIntOrNull() ?: 0)
                }

            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isNum1Valid && isNum2Valid // Disable button if either number is invalid
        ) {
            Text("Add Numbers")
        }

        // Display the sum
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
