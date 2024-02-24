package com.example.myapplication.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.myapplication.shared.Repository
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.shared.`bottom-bar`.Config
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.`locker-set-key`.LockerSetKeyComponent

interface RootComponent {
    val stack: Value<ChildStack<Config, Child>>
    val repository: Repository

    fun onBackClicked(toIndex: Int)

    fun onTabClick(tab: BottomBarTabEnum)

    sealed class Child {
        class Hope(val component: TabComponent) : Child()
        class WeHave(val component: TabComponent) : Child()
        class AChance(val component: TabComponent) : Child()
        class LockerSetKey(val component: LockerSetKeyComponent) : Child()
    }
}

fun RootComponent.Child.toBottomBarEnum() = when (this) {
    is RootComponent.Child.AChance -> BottomBarTabEnum.aChance
    is RootComponent.Child.Hope -> BottomBarTabEnum.hope
    is RootComponent.Child.LockerSetKey -> BottomBarTabEnum.hidden
    is RootComponent.Child.WeHave -> BottomBarTabEnum.weHave
}
