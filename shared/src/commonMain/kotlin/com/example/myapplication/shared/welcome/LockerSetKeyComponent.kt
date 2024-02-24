package com.example.myapplication.shared.welcome

interface LockerSetKeyComponent {
    val title: String

    fun onSetKeyClick(key: Int)
    fun onBackClicked()
}
