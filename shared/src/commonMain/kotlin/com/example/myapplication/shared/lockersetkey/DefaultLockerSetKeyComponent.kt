package com.example.myapplication.shared.lockersetkey

import com.arkivanov.decompose.ComponentContext
import com.example.myapplication.shared.lifecycle.LockerViewModel

class DefaultLockerSetKeyComponent(
    private val componentContext: ComponentContext,
    private val lockerViewModel: LockerViewModel,
    private val onFinished: () -> Unit,
) : LockerSetKeyComponent, ComponentContext by componentContext {
    private val uiState = lockerViewModel.uiState.value
    override val title = "Selecting a key for the locker #${uiState.pickedLocker}"

    override fun onSetKeyClick(key: Int) {
        lockerViewModel.setKey(
            tabEnum = uiState.previousTab,
            locker = uiState.pickedLocker,
            key = key,
        )
        onBackClicked()
    }

    override fun onBackClicked() = onFinished()
}
