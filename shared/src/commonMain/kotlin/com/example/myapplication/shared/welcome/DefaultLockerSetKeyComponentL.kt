package com.example.myapplication.shared.welcome

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.example.myapplication.shared.getPlatformName
import com.example.myapplication.shared.welcome.LockerSetKeyComponentL.Model

class DefaultLockerSetKeyComponentL(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : LockerSetKeyComponentL, ComponentContext by componentContext {
    // Consider preserving and managing the state via a store
    private val state = MutableValue(Model())
    override val model: Value<Model> = state

    override fun onUpdateGreetingText() {
        state.update {
            // TODO: Use down string, as title
            "Selecting a key for the locker #$"
            it.copy(greetingText = "Welcome from ${getPlatformName()}")
        }
    }

    override fun onBackClicked() {
        onFinished()
    }
}
