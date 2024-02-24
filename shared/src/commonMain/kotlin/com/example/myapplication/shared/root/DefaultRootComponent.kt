package com.example.myapplication.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.example.myapplication.shared.Repository
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.shared.`bottom-bar`.Config
import com.example.myapplication.shared.main.DefaultTabComponent
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.root.RootComponent.Child
import com.example.myapplication.shared.welcome.DefaultLockerSetKeyComponent
import com.example.myapplication.shared.welcome.LockerSetKeyComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val repository = Repository()
    private val navigation = StackNavigation<Config>()

    // TODO: enter BottomBarTabEnum here?
    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
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
                tab = BottomBarTabEnum.hope,
                componentContext = childComponentContext,
                onTabClick = {
                    navigation.bringToFront(
                        Config.Hope
                    )
                }
            )
        )

        Config.WeHave -> Child.WeHave(
            tabComponent(
                tab = BottomBarTabEnum.weHave,
                componentContext = childComponentContext,
                onTabClick = {
                    navigation.bringToFront(
                        Config.WeHave
                    )
                }
            )
        )

        Config.AChance -> Child.AChance(
            tabComponent(
                tab = BottomBarTabEnum.aChance,
                componentContext = childComponentContext,
                onTabClick = {
                    navigation.bringToFront(
                        Config.AChance
                    )
                }
            )
        )

        Config.Hidden -> Child.LockerSetKey(
            lockerSetKeyComponent(
                componentContext = childComponentContext
            )
        )
    }

    private fun tabComponent(
        tab: BottomBarTabEnum,
        componentContext: ComponentContext,
        onTabClick: (BottomBarTabEnum) -> Unit
    ): TabComponent = DefaultTabComponent(
        componentContext = componentContext,
        onLockerSetKey = {
            navigation.bringToFront(
                Config.Hidden
            )
        },
        onTab = onTabClick
    )

    private fun lockerSetKeyComponent(
        componentContext: ComponentContext
    ): LockerSetKeyComponent = DefaultLockerSetKeyComponent(
        componentContext = componentContext,
        onFinished = navigation::pop,
    )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }
}
