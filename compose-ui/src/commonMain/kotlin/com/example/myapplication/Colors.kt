package com.example.myapplication

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalPallet =
    compositionLocalOf<LightPallet> { error("No local pallet") }

data class LightPallet(
    val accent: Color = Color(0xFF00FFFFF),
    val background: Color = Color.White
)
