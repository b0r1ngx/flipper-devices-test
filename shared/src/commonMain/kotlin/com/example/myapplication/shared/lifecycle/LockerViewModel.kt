package com.example.myapplication.shared.lifecycle

import com.example.myapplication.shared.datalayer.LockerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LockerViewModel(
    initialLockerUiState: LockerUiState,
): DecomposeViewModel() {
    private val _uiState = MutableStateFlow(initialLockerUiState)
    val uiState: StateFlow<LockerUiState> = _uiState.asStateFlow()
}