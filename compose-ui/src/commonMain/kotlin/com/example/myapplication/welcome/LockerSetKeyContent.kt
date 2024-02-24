package com.example.myapplication.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.example.myapplication.LocalPallet
import com.example.myapplication.`key-selection`.KeySelectionScreen
import com.example.myapplication.shared.welcome.LockerSetKeyComponent
import com.example.myapplication.`android-utils`.LocalChangeBarsColors

@Composable
internal fun LockerSetKeyContent(
    component: LockerSetKeyComponent,
    modifier: Modifier = Modifier,
) {
    val pallet = LocalPallet.current
    val changeBarsColor = LocalChangeBarsColors.current
    SideEffect { changeBarsColor(pallet) }
    Scaffold(modifier = modifier) {
        KeySelectionScreen(
            title = component.title,
            lockers = mutableMapOf("a" to null),
            onLockerSetKeyClick = component::onSetKeyClick,
            modifier = Modifier.background(pallet.accent)
        )
    }
}
