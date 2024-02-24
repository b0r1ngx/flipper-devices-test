package com.example.myapplication.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.LocalPallet
import com.example.myapplication.`key-selection`.KeySelectionScreen
import com.example.myapplication.shared.welcome.LockerSetKeyComponent

@Composable
internal fun LockerSetKeyContent(
    component: LockerSetKeyComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .background(LocalPallet.current.accent)
            .statusBarsPadding(),
    ) {
        KeySelectionScreen(
            title = component.title,
            lockers = mutableMapOf("a" to null),
            onLockerSetKeyClick = component::onSetKeyClick
        )
    }
}
