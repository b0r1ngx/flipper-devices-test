package com.example.myapplication.`key-selection`

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.RawEntry
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.Title

@Composable
fun KeySelectionScreen(
    title: String,
    lockers: MutableMap<String, String?>,
    onLockerSetKeyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Title(title = title)
        Data(
            lockers = lockers,
            onLockerSetKeyClick = onLockerSetKeyClick
        )
    }
}

@Composable
private fun Title(
    title: String,
    modifier: Modifier = Modifier
) = Text(
    text = title,
    modifier = modifier.padding(15.dp),
    style = Title,
)


@Composable
private fun Data(
    lockers: MutableMap<String, String?>,
    onLockerSetKeyClick: () -> Unit
) {
    val rememberLockers = remember { lockers.toList() }
    LazyColumn {
        itemsIndexed(rememberLockers) { i, it ->
            Locker(
                locker = it.first,
                key = it.second,
                onClick = onLockerSetKeyClick
            )
            if (i < rememberLockers.lastIndex)
                Divider(
                    color = Color.Black,
                    thickness = 1.dp
                )
        }
    }
}

@Composable
private fun Locker(
    locker: String,
    key: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = locker, style = RawEntry)
        if (key != null) {
            Text(text = key, style = RawEntry)
        }
    }
}
