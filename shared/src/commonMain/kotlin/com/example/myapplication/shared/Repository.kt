package com.example.myapplication.shared

import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

private const val DATA_SIZE = 15

class Repository {
    private val _data: MutableList<Pair<BottomBarTabEnum, MutableMap<Int, Int?>>> =
        mutableListOf()
    val lockerData: List<Pair<BottomBarTabEnum, MutableMap<Int, Int?>>>

    val keys = List<Pair<Int, Int?>>(15) { it + 1 to null }

    init {
        BottomBarTabEnum.entries.dropLast(1).forEach {
            val map = mutableMapOf<Int, Int?>()
            repeat(DATA_SIZE) {
                map[it + 1] = null
            }
            _data.add(it to map)
        }
        lockerData = _data.toList()
    }

    fun setKey(
        tabEnum: BottomBarTabEnum,
        locker: Int,
        key: Int
    ) {
        lockerData[tabEnum.ordinal]
            .second[locker] = key
    }
}