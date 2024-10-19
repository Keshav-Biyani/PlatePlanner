package com.example.plateplanner

import android.content.ContentValues.TAG
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.plateplanner.data.Shoppinglist
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val gptQuery = mutableStateOf("")
    private val api = BuildConfig.API_KEY
    val word = mutableStateOf("")
    private val _dataList = MutableStateFlow<Shoppinglist?>(null)
    val data: StateFlow<Shoppinglist?> = _dataList

    // Add loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    @OptIn(BetaOpenAI::class)
    fun getGPTResponse(textToSpeech: TextToSpeech) {
        viewModelScope.launch {
            val openAI = OpenAI(api)

            try {
                val chatCompletionRequest = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(
                        ChatMessage(
                            role = ChatRole.User,
                            content = gptQuery.value
                        )
                    )
                )

                val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

                val response = completion.choices.first().message?.content
                val gson = Gson()
                val list = gson.fromJson(response, Shoppinglist::class.java)
                _dataList.value = list

                say(textToSpeech, response)
            } catch (e: Exception) {
                Log.d(TAG, "getGPTResponse: ERROR: ${e.message ?: ""}")
            }
        }
    }

    private fun say(textToSpeech: TextToSpeech, response: String?) {
        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onRangeStart(utteranceId: String?, start: Int, end: Int, frame: Int) {
                super.onRangeStart(utteranceId, start, end, frame)

                word.value = "${word.value} ${response?.substring(start, end) ?: ""}"
            }

            override fun onStart(p0: String?) {
                word.value = ""
            }

            override fun onDone(p0: String?) {}

            override fun onError(p0: String?) {}
        })

        textToSpeech.speak(
            response,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "utterance_id"
        )
    }


}