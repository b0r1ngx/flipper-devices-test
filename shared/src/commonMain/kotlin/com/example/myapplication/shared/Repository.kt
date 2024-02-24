package com.example.myapplication.shared

import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

private const val Locker = "Locker"
private const val KeyNotFound = "Key not found"
private const val DATA_SIZE = 15

class Repository {
    private val _data: MutableList<Pair<BottomBarTabEnum, MutableMap<String, String?>>> =
        mutableListOf()
    val data = _data.toList()

    init {
        BottomBarTabEnum.entries.forEach {
            val map = mutableMapOf<String, String?>()
            repeat(DATA_SIZE) {
                map["$Locker #${it + 1}"] = KeyNotFound
            }
            _data.add(it to map)
        }
    }
}