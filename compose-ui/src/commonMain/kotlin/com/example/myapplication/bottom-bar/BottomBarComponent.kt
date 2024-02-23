package com.example.myapplication.`bottom-bar`

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

@Composable
fun BottomBarComponent(
    selectedItem: BottomBarTabEnum,
    onBottomBarClick: (BottomBarTabEnum) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = remember { BottomBarTabEnum.entries.toTypedArray() }
    var selectedIndex by remember(selectedItem) {
        mutableIntStateOf(tabs.indexOf(selectedItem))
    }
    var tabPositions by remember {
        mutableStateOf(emptyList<TabPosition>())
    }
    TabRow(
        modifier = modifier
            .fillMaxWidth(),
//            .onGloballyPositioned {
//                tabHeightPx = it.size.height
//            },
        backgroundColor = Color.Transparent,
        selectedTabIndex = selectedIndex,
//        contentColor = LocalPallet.current.bottomBarContent,
        indicator = { tabPositions = it },
        // remove bottom divider from tabRow
//        divider = { }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                tab = tab,
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onBottomBarClick(tabs[index])
                }
            )
        }
    }
}

@Composable
private fun Tab(
    tab: BottomBarTabEnum,
    selected: Boolean,
    onClick: (() -> Unit),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Image(
//            tab.secondName
//        )
        Text(text = tab.secondName)
    }
}