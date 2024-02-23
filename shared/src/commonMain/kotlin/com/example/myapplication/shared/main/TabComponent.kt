package com.example.myapplication.shared.main

import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

interface TabComponent {
    fun onLockerSetKeyClicked()
    fun onTabClicked(tab: BottomBarTabEnum)
}
