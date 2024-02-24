package com.example.myapplication

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalPallet =
    compositionLocalOf<LightPallet> { error("No local pallet") }

data class LightPallet(
    val accent: Color = Color(0xff00ffff),
    val background: Color = Color.White
)

data class DarkPallet(
    val accent: Color = Color(0xFF3b83bd),
    val background: Color = Color.Black
)