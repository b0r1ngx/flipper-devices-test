package com.example.myapplication.`android-utils`

import androidx.compose.runtime.compositionLocalOf
import com.example.myapplication.Pallet

val LocalChangeBarsColors =
    compositionLocalOf<(pallet: Pallet) -> Unit> { error("No function available") }