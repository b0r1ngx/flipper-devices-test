package com.example.myapplication.android

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.arkivanov.decompose.defaultComponentContext
import com.example.myapplication.LocalPallet
import com.example.myapplication.LightPallet
import com.example.myapplication.root.RootContent
import com.example.myapplication.shared.root.DefaultRootComponent
import com.example.myapplication.`android-utils`.LocalChangeBarsColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightPallet = LightPallet()
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext()
        )
        setContent {
            CompositionLocalProvider(LocalChangeBarsColors provides ::changeBarsColors) {
                CompositionLocalProvider(LocalPallet provides lightPallet) {
                    RootContent(component = root)
                }
            }
        }
    }

    private fun changeBarsColors(color: Color) {
        window.navigationBarColor = color.toArgb()
        window.statusBarColor = color.toArgb()
    }
}
