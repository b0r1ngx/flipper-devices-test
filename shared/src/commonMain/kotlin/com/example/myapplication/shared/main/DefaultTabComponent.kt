package com.example.myapplication.shared.main

import com.arkivanov.decompose.ComponentContext

class DefaultTabComponent(
    componentContext: ComponentContext,
    private val onLockerSetKey: (key: Int) -> Unit,
) : TabComponent, ComponentContext by componentContext {
    override fun onLockerSetKeyClick(key: Int) {
        onLockerSetKey(key)
    }
}
