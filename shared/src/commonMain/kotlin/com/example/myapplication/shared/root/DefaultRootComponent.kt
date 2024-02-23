package com.example.myapplication.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.shared.main.DefaultTabComponent
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.root.RootComponent.Child
import com.example.myapplication.shared.welcome.DefaultLockerSetKeyComponent
import com.example.myapplication.shared.welcome.WelcomeComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    // TODO: enter BottomBarTabEnum here?
    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Hope,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(
        config: Config,
        childComponentContext: ComponentContext
    ): Child = when (config) {
        is Config.Hope -> Child.Hope(
            tabComponent(
                childComponentContext,
                onTabClick = { navigation.bringToFront(Config.Hope) }
            )
        )

        Config.WeHave -> Child.WeHave(
            tabComponent(
                childComponentContext,
                onTabClick = { navigation.bringToFront(Config.WeHave) }
            )
        )

        Config.AChance -> Child.AChance(
            tabComponent(
                childComponentContext,
                onTabClick = { navigation.bringToFront(Config.AChance) }
            )
        )

        is Config.LockerSetKey -> Child.LockerSetKey(
            lockerSetKeyComponent(childComponentContext)
        )
    }

    private fun tabComponent(
        componentContext: ComponentContext,
        onTabClick: (BottomBarTabEnum) -> Unit
    ): TabComponent = DefaultTabComponent(
        componentContext = componentContext,
        onLockerSetKeyClick = { navigation.push(Config.LockerSetKey) },
        onTabClick = onTabClick
    )

    private fun lockerSetKeyComponent(
        componentContext: ComponentContext
    ): WelcomeComponent = DefaultLockerSetKeyComponent(
        componentContext = componentContext,
        onFinished = navigation::pop,
    )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    private sealed interface Config : Parcelable {
        val enum: BottomBarTabEnum

        @Parcelize
        data object Hope : Config {
            override val enum: BottomBarTabEnum
                get() = BottomBarTabEnum.hope
        }

        @Parcelize
        data object WeHave : Config {
            override val enum: BottomBarTabEnum
                get() = BottomBarTabEnum.weHave
        }

        @Parcelize
        data object AChance : Config {
            override val enum: BottomBarTabEnum
                get() = BottomBarTabEnum.aChance
        }

        @Parcelize
        data object LockerSetKey : Config {
            override val enum: BottomBarTabEnum
                get() = BottomBarTabEnum.hidden
        }
    }
}
