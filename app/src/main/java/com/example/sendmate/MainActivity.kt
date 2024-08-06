package com.example.sendmate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sendmate.ui.theme.SendMateTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SendMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().padding(top = 10.dp, start =  10.dp, end = 10.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SendMateApp(this)
                }
            }
        }
    }
}