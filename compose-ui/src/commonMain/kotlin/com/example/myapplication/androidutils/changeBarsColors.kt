package com.example.myapplication.androidutils

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalChangeBarsColors =
    compositionLocalOf<(color: Color) -> Unit> { error("No function available") }