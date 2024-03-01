package com.example.myapplication.shared.lifecycle

import com.example.myapplication.shared.bottombar.BottomBarTabEnum
import com.example.myapplication.shared.datalayer.LockerUiState
import com.example.myapplication.shared.datalayer.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LockerViewModel(
    // Also may be injected at ComponentActivity of Android
    private val repository: Repository = Repository(),
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
                pickedLocker = pickedLocker,
            )
        }
        navigateTo()
    }

    fun setKey(
        tabEnum: BottomBarTabEnum,
        locker: Int,
        key: Int
    ) {
        repository.setKey(
            tabEnum = tabEnum,
            locker = locker,
            key = key,
        )
    }

    fun getLockersToKeysAsList(tab: BottomBarTabEnum): List<Pair<Int, Int?>> {
        return repository.getLockersToKeysAsList(tab = tab)
    }

    fun getKeys(): List<Pair<Int, Int?>> = repository.keys
}