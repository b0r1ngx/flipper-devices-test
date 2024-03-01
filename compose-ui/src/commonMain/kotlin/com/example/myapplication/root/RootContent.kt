package com.example.myapplication.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.myapplication.bottombar.BottomBarComponent
import com.example.myapplication.lockersetkey.LockerSetKeyContent
import com.example.myapplication.main.TabContent
import com.example.myapplication.shared.root.RootComponent
import com.example.myapplication.shared.root.RootComponent.Child

private const val TAG = "RootContent:"

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val lockerViewModel = component.lockerViewModel
    val childStack by component.stack.subscribeAsState()
    val selectedTab = childStack.active.configuration.enum
    val bottomBarState = rememberSaveable {
        mutableStateOf(lockerViewModel.uiState.value.isLockerSetKey)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBarComponent(
                selectedItem = selectedTab,
                bottomBarState = bottomBarState,
                onBottomBarClick = component::onTabClick,
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Children(
                stack = component.stack,
                modifier = Modifier.fillMaxSize(),
            ) {
                // TODO: Not work with bottomBarState, like that?
                bottomBarState.value = true
                when (val instance = it.instance) {
                    // can't usage few enumeration in one is,
                    // because then we have instance.component as a Child class
                    // TODO: workaround, Child.LockerSetKey must be as component of BottomBar
                    is Child.Hope -> TabContent(
                        component = instance.component,
                        childStack = childStack,
                        entries = lockerViewModel.getLockersToKeysAsList(selectedTab),
                    )

                    is Child.WeHave -> TabContent(
                        component = instance.component,
                        childStack = childStack,
                        entries = lockerViewModel.getLockersToKeysAsList(selectedTab),
                    )

                    is Child.AChance -> TabContent(
                        component = instance.component,
                        childStack = childStack,
                        entries = lockerViewModel.getLockersToKeysAsList(selectedTab),
                    )

                    is Child.LockerSetKey -> {
                        bottomBarState.value = false
                        LockerSetKeyContent(
                            component = instance.component,
                            keys = lockerViewModel.getKeys()
                        )
                    }
                }
            }
        }
    }
}
