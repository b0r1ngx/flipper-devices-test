package com.example.myapplication.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.welcome.WelcomeComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Hope(val component: TabComponent) : Child()
        class WeHave(val component: TabComponent) : Child()
        class AChance(val component: TabComponent) : Child()
        class LockerSetKey(val component: WelcomeComponent) : Child()
    }
}
