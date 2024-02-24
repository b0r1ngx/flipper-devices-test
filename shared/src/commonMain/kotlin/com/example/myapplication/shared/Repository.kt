package com.example.myapplication.shared

import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

private const val Locker = "Locker #"
const val Key = "Key #"
private const val KeyNotFound = "Key not found"
private const val DATA_SIZE = 15

class Repository {
    private val _data: MutableList<Pair<BottomBarTabEnum, MutableMap<String, String?>>> =
        mutableListOf()
    val lockerData: List<Pair<BottomBarTabEnum, MutableMap<String, String?>>>

    val keys = List(15) { it + 1 }

    init {
        BottomBarTabEnum.entries.dropLast(1).forEach {
            val map = mutableMapOf<String, String?>()
            repeat(DATA_SIZE) {
                map["$Locker${it + 1}"] = KeyNotFound
            }
            _data.add(it to map)
        }
        lockerData = _data.toList()
    }
}