package com.example.myapplication.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.example.myapplication.shared.datalayer.Repository
import com.example.myapplication.shared.bottombar.BottomBarTabEnum
import com.example.myapplication.shared.bottombar.Config
import com.example.myapplication.shared.datalayer.LockerUiState
import com.example.myapplication.shared.lifecycle.LockerViewModel
import com.example.myapplication.shared.lifecycle.viewModelWithFactoryWithoutRemember
import com.example.myapplication.shared.lockersetkey.DefaultLockerSetKeyComponent
import com.example.myapplication.shared.lockersetkey.LockerSetKeyComponent
import com.example.myapplication.shared.main.DefaultTabComponent
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.root.RootComponent.Child

// TODO: Found a not same behaviour from reference,
//        when we navigate few times at BottomBar,
//        stack of navigation is increasing
//     - want to fix it now
class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    // TODO: Provide Repository
    override val repository = Repository()

    // TODO: Provide Navigation?
    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Hope,
        handleBackButton = true,
        childFactory = ::child,
    )

    private val lockerViewModel = viewModelWithFactoryWithoutRemember(key = this) {
        LockerViewModel(
            initialLockerUiState = LockerUiState(
                previousTab = stack.active.configuration.enum,
                pickedLocker = 0,
            )
        )
    }

    private fun child(
        config: Config,
        childComponentContext: ComponentContext
    ): Child = when (config) {
        is Config.Hope -> Child.Hope(
            tabComponent(
                tab = BottomBarTabEnum.hope,
                componentContext = childComponentContext,
            )
        )

        Config.WeHave -> Child.WeHave(
            tabComponent(
                tab = BottomBarTabEnum.weHave,
                componentContext = childComponentContext,
            )
        )

        Config.AChance -> Child.AChance(
            tabComponent(
                tab = BottomBarTabEnum.aChance,
                componentContext = childComponentContext,
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
    ): TabComponent = DefaultTabComponent(
        componentContext = componentContext,
        onLockerSetKey = { pickedLockerKey ->
            lockerViewModel.onLockerSetKeyNavigate(
                previousTab = tab,
                pickedLocker = pickedLockerKey,
                navigateTo = {
                    navigation.bringToFront(
                        Config.Hidden
                    )
                }
            )
        }
    )


    private fun lockerSetKeyComponent(
        componentContext: ComponentContext
    ): LockerSetKeyComponent = DefaultLockerSetKeyComponent(
        componentContext = componentContext,
//        lockerViewModel = lockerViewModel,
        tabEnum = lockerViewModel.uiState.value.previousTab,
        locker = lockerViewModel.uiState.value.pickedLocker,
        repository = repository,
        onFinished = navigation::pop,
    )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    override fun onTabClick(tab: BottomBarTabEnum) {
        // TODO: pushToFront / bringToFront - works same?
        //       and in both variants of usage, its doesn't helps me
        //       to make same logic, that when we navigate few times at BottomBar
        //       not increase a navigation stack
        navigation.bringToFront(
            configuration = tab.toConfig()
        )
    }
}
