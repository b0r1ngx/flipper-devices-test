package com.example.myapplication.shared.main

import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

interface TabComponent {
    fun onLockerSetKeyClick(key: Int)
    fun onTabClick(tab: BottomBarTabEnum)
}
