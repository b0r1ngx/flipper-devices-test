package com.example.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.arkivanov.decompose.defaultComponentContext
import com.example.myapplication.LocalPallet
import com.example.myapplication.LightPallet
import com.example.myapplication.root.RootContent
import com.example.myapplication.shared.root.DefaultRootComponent
import com.example.myapplication.`android-utils`.LocalChangeBarsColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pallet = LightPallet()
        val viewModel: AppViewModel by viewModels()
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext()
        )
        setContent {
            CompositionLocalProvider(LocalPallet provides pallet) {
                CompositionLocalProvider(LocalChangeBarsColors provides ::changeBarsColors) {
                    CompositionLocalProvider(LocalPallet provides pallet) {
                        RootContent(component = root)
                    }
                }
            }
        }
    }

    private fun changeBarsColors(color: Color) {
        window.navigationBarColor = color.toArgb()
        window.statusBarColor = color.toArgb()
    }
}
