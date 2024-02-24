package com.example.myapplication.`bottom-bar`

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import com.example.myapplication.BottomBarEntry
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

@Composable
fun BottomBarComponent(
    selectedItem: BottomBarTabEnum,
    onBottomBarClick: (BottomBarTabEnum) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = remember {
        BottomBarTabEnum.entries.toTypedArray()
    }.dropLast(1)
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
            )
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = tab.icon(),
            contentDescription = tab.secondName
        )
        Text(
            text = tab.secondName,
            style = BottomBarEntry
        )
        if (selected) {
            Divider(
                color = Color.Black,
                thickness = 1.dp
            )
        }
    }
}

private fun BottomBarTabEnum.icon() = when (this) {
    BottomBarTabEnum.hope -> Icons.Default.Home
    BottomBarTabEnum.weHave -> Icons.Default.Add
    BottomBarTabEnum.aChance -> Icons.Default.Call
    BottomBarTabEnum.hidden -> Icons.Default.Search
}