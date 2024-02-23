package com.example.myapplication.shared

import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

private const val Locker = "Locker"
private const val KeyNotFound = "Key not found"
private const val DATA_SIZE = 15

class Repository {
    // TODO: @ReadOnly / Immutable / Stable
    val data: MutableList<Pair<BottomBarTabEnum, MutableMap<String, String>>> = mutableListOf()

    init {
        BottomBarTabEnum.entries.forEach {
            val map = mutableMapOf<String, String>()
            repeat(DATA_SIZE) {
                map[Locker] = KeyNotFound
            }
            data.add(it to map)
        }
    }
}