package com.example.passeio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.passeio.ui.theme.PasseioTheme
import com.example.passeio.ui.viewmodel.CounterState
import com.example.passeio.ui.viewmodel.CounterViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passeio.ui.viewmodel.CounterEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasseioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel()
) {
    val counterState by counterViewModel.getState().collectAsStateWithLifecycle()
    Counter(
        modifier = modifier,
        counterState = counterState,
        onIncrementButtonPressed = {
            counterViewModel.handle(CounterEvent.Increment)
        }
    )
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    counterState: CounterState,
    onIncrementButtonPressed: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            counterState.count.toString(),
            style = MaterialTheme.typography.headlineLarge
        )
        Button(onClick = onIncrementButtonPressed) {
            Text(stringResource(R.string.increment))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    PasseioTheme {
        Counter(
            counterState = CounterState(),
            onIncrementButtonPressed = {

            }
        )
    }
}
