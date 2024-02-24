package com.example.myapplication.shared.`locker-set-key`

interface LockerSetKeyComponent {
    val title: String

    fun onSetKeyClick(key: Int)
    fun onBackClicked()
}
