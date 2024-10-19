package com.example.plateplanner

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.plateplanner.navigation.Navigation
import com.example.plateplanner.ui.theme.PlatePlannerTheme
import com.google.gson.internal.GsonBuildConfig
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
@AndroidEntryPoint
class MainActivity : ComponentActivity() , TextToSpeech.OnInitListener {
    private lateinit var ttsObject: TextToSpeech



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ttsObject = TextToSpeech(this, this)

        setContent {
            PlatePlannerTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MyGPT(ttsObject)
                    Navigation(ttsObject)
                   // HomeScreenStateful(ttsObject)
                }
            }
        }
    }

    override fun onInit(status: Int) {
        val result = ttsObject.setLanguage(Locale.ENGLISH)
        if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.d("TTS", "onInit: Error langugae not supported")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlatePlannerTheme {
        Greeting("Android")
    }
}