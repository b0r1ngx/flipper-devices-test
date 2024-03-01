package com.example.myapplication.shared.datalayer

import com.example.myapplication.shared.bottombar.BottomBarTabEnum

private const val DATA_SIZE = 15

class Repository {
    private val _lockersData: MutableList<Pair<BottomBarTabEnum, MutableMap<Int, Int?>>> =
        mutableListOf()
    val lockersData: List<Pair<BottomBarTabEnum, MutableMap<Int, Int?>>>

    val keys = List<Pair<Int, Int?>>(15) { it + 1 to null }

    init {
        BottomBarTabEnum.entries.dropLast(1).forEach {
            val map = mutableMapOf<Int, Int?>()
            repeat(DATA_SIZE) {
                map[it + 1] = null
            }
            _lockersData.add(it to map)
        }
        lockersData = _lockersData.toList()
    }

    fun setKey(
        tabEnum: BottomBarTabEnum,
        locker: Int,
        key: Int
    ) {
        lockersData[tabEnum.ordinal].second[locker] = key
    }

    fun getLockersToKeysAsList(tab: BottomBarTabEnum): List<Pair<Int, Int?>> {
        return lockersData.find { it.first == tab }!!.second.toList()
    }
}