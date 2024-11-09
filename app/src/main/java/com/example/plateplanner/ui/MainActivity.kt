package com.example.plateplanner.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.plateplanner.navigation.Navigation
import com.example.plateplanner.ui.theme.PlatePlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  //  private lateinit var ttsObject: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // ttsObject = TextToSpeech(this, this)

        setContent {
            PlatePlannerTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }

//    override fun onInit(status: Int) {
//        val result = ttsObject.setLanguage(Locale.ENGLISH)
//        if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
//            Log.d("TTS", "onInit: Error langugae not supported")
//        }
//    }
}
