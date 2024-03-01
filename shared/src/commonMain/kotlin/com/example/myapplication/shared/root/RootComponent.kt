package com.example.myapplication.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.myapplication.shared.bottombar.BottomBarTabEnum
import com.example.myapplication.shared.bottombar.Config
import com.example.myapplication.shared.lifecycle.LockerViewModel
import com.example.myapplication.shared.lockersetkey.LockerSetKeyComponent
import com.example.myapplication.shared.main.TabComponent

interface RootComponent {
    val stack: Value<ChildStack<Config, Child>>
    val lockerViewModel: LockerViewModel

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
