package com.example.myapplication.shared.main

import com.arkivanov.decompose.ComponentContext
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

class DefaultTabComponent(
    componentContext: ComponentContext,
    private val onLockerSetKey: (key: Int) -> Unit,
    private val onTab: (BottomBarTabEnum) -> Unit,
) : TabComponent, ComponentContext by componentContext {
    override fun onLockerSetKeyClick(key: Int) {
        onLockerSetKey(key)
    }

    override fun onTabClick(tab: BottomBarTabEnum) {
        onTab(tab)
    }
}
