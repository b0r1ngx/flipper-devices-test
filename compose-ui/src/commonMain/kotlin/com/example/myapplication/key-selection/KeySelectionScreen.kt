package com.example.myapplication.`key-selection`

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.RawEntry
import com.example.myapplication.Title

private const val Locker = "Locker #"
private const val Key = "Key #"
private const val KeyNotFound = "Key not found"

@Composable
fun KeySelectionScreen(
    title: String,
    entries: List<Pair<Int, Int?>>,
    onLockerSetKeyClick: (key: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Title(title = title)
        Data(
            title = title,
            entries = entries,
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
    title: String,
    entries: List<Pair<Int, Int?>>,
    onLockerSetKeyClick: (key: Int) -> Unit
) {
    LazyColumn {
        items(entries) {
            Divider(
                color = Color.Black,
                thickness = 1.dp
            )
            Locker(
                title = title,
                locker = it.first,
                key = it.second,
                onClick = onLockerSetKeyClick
            )
        }
    }
}

@Composable
private fun Locker(
    title: String,
    locker: Int,
    key: Int?,
    onClick: (key: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: do something with that:C
    //       in real world, I must re-develop this thing
    val isLockerSetKeyScreen = title.first() == 'S'

    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .clickable {
                onClick(locker)
            }
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLockerSetKeyScreen) {
            Text(text = "$Key$locker", style = RawEntry)
        } else {
            Text(text = "$Locker$locker", style = RawEntry)
            if (key != null) {
                Text(text = "$Key$key", style = RawEntry)
            } else {
                Text(text = KeyNotFound, style = RawEntry)
            }
        }
    }
}
