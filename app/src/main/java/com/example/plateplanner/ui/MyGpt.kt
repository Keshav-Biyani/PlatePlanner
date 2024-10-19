package com.example.plateplanner

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MyGPT(
    textToSpeech: TextToSpeech,
    viewModel : MainViewModel = hiltViewModel()
) {
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GPTField(viewModel.gptQuery)

            Spacer(modifier = Modifier.height(16.dp))

            GetGPTButton {
                viewModel.getGPTResponse(textToSpeech)
            }

            GPTResponse(response = viewModel.word.value)
        }
    }
}
@Composable
fun GPTResponse(
    response: String? = null
) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = response ?: "No GPT Response"
    )
}
@Composable
fun GetGPTButton(getGPTResponse: (() -> Unit)? = null) {
    Button(
        onClick = {
            getGPTResponse?.invoke()
        }
    ) {
        Text(text = "Get GPT Response")
    }
}
@Preview
@Composable
private fun GPTFieldPreview() {
    GPTField()
}

@Composable
fun GPTField(
    gptQuery: MutableState<String>? = null
) {
    val text = remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.wrapContentSize(),
        value = text.value,
        onValueChange = {
            text.value = it
            gptQuery?.value = it
        }
    )
}