package com.example.myapplication.shared.datalayer

import com.example.myapplication.shared.bottombar.BottomBarTabEnum

data class LockerUiState(
    val previousTab: BottomBarTabEnum,
    val pickedLocker: Int,
)