package com.example.myapplication.shared.model

import com.example.myapplication.shared.bottombar.BottomBarTabEnum

data class LockerUiState(
    val previousTab: BottomBarTabEnum,
    val pickedLocker: Int,
    val isLockerSetKey: Boolean
)