package com.example.myapplication.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.myapplication.`bottom-bar`.BottomBarComponent
import com.example.myapplication.main.TabContent
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.shared.root.RootComponent
import com.example.myapplication.shared.root.RootComponent.Child
import com.example.myapplication.shared.root.toBottomBarEnum
import com.example.myapplication.welcome.LockerSetKeyContent

private const val TAG = "RootContent:"

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.stack.subscribeAsState()
    val selectedTab = childStack.active.configuration.enum
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBarComponent(
                selectedItem = selectedTab,
                bottomBarState = bottomBarState,
                onBottomBarClick = component::onTabClick
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Children(
                stack = component.stack,
                modifier = Modifier.fillMaxSize(),
            ) {
                bottomBarState.value = true
                when (val instance = it.instance) {
                    is Child.Hope -> TabContent(
                        tab = BottomBarTabEnum.hope,
                        component = instance.component,
                        childStack = childStack,
                        entries = component.repository.lockerData.find {
                            it.first == BottomBarTabEnum.hope
                        }!!.second.toList(),
                    )

                    is Child.WeHave -> TabContent(
                        tab = BottomBarTabEnum.weHave,
                        component = instance.component,
                        childStack = childStack,
                        entries = component.repository.lockerData.find {
                            it.first == BottomBarTabEnum.weHave
                        }!!.second.toList(),
                    )

                    is Child.AChance -> TabContent(
                        tab = BottomBarTabEnum.aChance,
                        component = instance.component,
                        childStack = childStack,
                        entries = component.repository.lockerData.find {
                            it.first == BottomBarTabEnum.aChance
                        }!!.second.toList(),
                    )

                    is Child.LockerSetKey -> {
                        bottomBarState.value = false
                        LockerSetKeyContent(
                            component = instance.component,
                            keys = component.repository.keys
                        )
                    }
                }
                println("$TAG ${component.repository}")
                println("$TAG ${component.repository.lockerData}")
            }
        }
    }
}
