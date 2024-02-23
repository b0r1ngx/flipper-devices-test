package com.example.myapplication.shared.main

import com.arkivanov.decompose.ComponentContext
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

class DefaultTabComponent(
    componentContext: ComponentContext,
    private val onLockerSetKeyClick: () -> Unit,
    private val onTabClick: (BottomBarTabEnum) -> Unit,
) : TabComponent, ComponentContext by componentContext {
    override fun onLockerSetKeyClicked() {
        onLockerSetKeyClick()
    }

    override fun onTabClicked(tab: BottomBarTabEnum) {
        onTabClick(tab)
    }
}
