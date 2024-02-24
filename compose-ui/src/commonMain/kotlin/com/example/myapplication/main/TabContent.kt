package com.example.myapplication.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    component: TabComponent,
    childStack: ChildStack<*, RootComponent.Child>,
    modifier: Modifier = Modifier,
) {
    val selectedTab = childStack.active.configuration
    Scaffold(
        modifier = modifier
            // TODO: need to change colors of system bars
            .background(LocalPallet.current.accent)
            .statusBarsPadding(),
        bottomBar = {
            BottomBarComponent(
                selectedItem = BottomBarTabEnum.hope,
                onBottomBarClick = component::onTabClicked
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                // TODO: need to change colors of system bars
                .background(LocalPallet.current.accent)
                .statusBarsPadding(),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = component::onLockerSetKeyClicked) {
                Text(text = "Show Welcome screen")
            }
        }
        KeySelectionScreen()
    }
}
