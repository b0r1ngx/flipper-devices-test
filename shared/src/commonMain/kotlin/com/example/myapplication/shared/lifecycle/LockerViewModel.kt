package com.example.myapplication.shared.lifecycle

import com.example.myapplication.shared.bottombar.BottomBarTabEnum
import com.example.myapplication.shared.datalayer.LockerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LockerViewModel(
    initialLockerUiState: LockerUiState,
) : DecomposeViewModel() {
    private val _uiState = MutableStateFlow(initialLockerUiState)
    val uiState: StateFlow<LockerUiState> = _uiState.asStateFlow()

    // TODO: optimize it?
    fun onLockerSetKeyNavigate(
        previousTab: BottomBarTabEnum,
        pickedLocker: Int,
        navigateTo: () -> Unit
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                previousTab = previousTab,
                pickedLocker = pickedLocker
            )
        }
        navigateTo()
    }
}