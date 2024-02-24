package com.example.myapplication.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.ChildStack
import com.example.myapplication.LocalPallet
import com.example.myapplication.`bottom-bar`.BottomBarComponent
import com.example.myapplication.`key-selection`.KeySelectionScreen
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.root.RootComponent

@Composable
internal fun TabContent(
    tab: BottomBarTabEnum,
    component: TabComponent,
    childStack: ChildStack<*, RootComponent.Child>,
    lockers: MutableMap<String, String?>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBarComponent(
                selectedItem = BottomBarTabEnum.hope,
                onBottomBarClick = component::onTabClick
            )
        }
    ) { paddingValues ->
        KeySelectionScreen(
            title = "${tab.secondName} tab",
            lockers = lockers,
            onLockerSetKeyClick = component::onLockerSetKeyClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}
