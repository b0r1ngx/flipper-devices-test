package com.example.myapplication.shared.welcome

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.example.myapplication.shared.Repository
import com.example.myapplication.shared.getPlatformName

class DefaultLockerSetKeyComponent(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : LockerSetKeyComponent, ComponentContext by componentContext {
    // Consider preserving and managing the state via a store
//    private val state = MutableValue(Model())
    override val title: String
        get() = "Selecting a key for the locker #$"

    override fun onSetKeyClick() {
//        state.update {
//            it.copy(greetingText = "Welcome from ${getPlatformName()}")
//        }
    }

    override fun onBackClicked() {
        onFinished()
    }
}
