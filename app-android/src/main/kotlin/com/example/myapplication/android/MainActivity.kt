package com.example.myapplication.android

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.toArgb
import com.arkivanov.decompose.defaultComponentContext
import com.example.myapplication.LocalPallet
import com.example.myapplication.Pallet
import com.example.myapplication.root.RootContent
import com.example.myapplication.shared.root.DefaultRootComponent
import com.example.myapplication.`android-utils`.LocalChangeBarsColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pallet = Pallet()
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext()
        )
        setContent {
            CompositionLocalProvider(LocalPallet provides pallet) {
                CompositionLocalProvider(LocalChangeBarsColors provides ::changeBarsColors) {
                    RootContent(component = root)
                }
            }
        }
    }

    private fun changeBarsColors(pallet: Pallet) {
        window.navigationBarColor = pallet.accent.toArgb()
        window.statusBarColor = pallet.accent.toArgb()
    }

    override fun onResume() {
        super.onResume()
        val decor = window?.decorView ?: return
        val uiMode = resources.configuration.uiMode
        val systemIsDark =
            (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

//        changeStatusBarContrastStyle(decor, isStatusBarIconLight(systemIsDark))
    }

    // TODO: ???
    private fun isStatusBarIconLight(systemIsDark: Boolean) = false

    @Suppress("Deprecated")
    private fun changeStatusBarContrastStyle(decor: View, isLightIcons: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = decor.windowInsetsController ?: return
            windowInsetsController.setSystemBarsAppearance(
                if (isLightIcons) {
                    0
                } else {
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                },
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            if (isLightIcons) {
                decor.systemUiVisibility =
                    decor.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                decor.systemUiVisibility =
                    decor.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}
