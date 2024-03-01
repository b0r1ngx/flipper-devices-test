package com.example.myapplication.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.ChildStack
import com.example.myapplication.LocalPallet
import com.example.myapplication.androidutils.LocalChangeBarsColors
import com.example.myapplication.keyselection.KeySelectionScreen
import com.example.myapplication.shared.main.TabComponent
import com.example.myapplication.shared.root.RootComponent
import com.example.myapplication.shared.root.toBottomBarEnum

@Composable
internal fun TabContent(
    component: TabComponent,
    childStack: ChildStack<*, RootComponent.Child>,
    entries: List<Pair<Int, Int?>>,
    modifier: Modifier = Modifier,
) {
    val selectedTab = childStack.active.instance.toBottomBarEnum()

    val pallet = LocalPallet.current
    val changeBarsColor = LocalChangeBarsColors.current
    SideEffect { changeBarsColor(pallet.background) }

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        KeySelectionScreen(
            title = "${selectedTab.secondName} tab",
            entries = entries,
            onLockerSetKeyClick = component::onLockerSetKeyClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}
