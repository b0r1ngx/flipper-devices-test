package com.example.myapplication

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalPallet = compositionLocalOf<Pallet> { error("No local pallet") }

data class Pallet(
    val accent: Color = Color(0xFF00FFFFF)
)
