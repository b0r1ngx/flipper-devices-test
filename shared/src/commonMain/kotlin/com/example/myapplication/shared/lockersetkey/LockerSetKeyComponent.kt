package com.example.myapplication.shared.lockersetkey

import com.arkivanov.decompose.value.MutableValue

interface LockerSetKeyComponent {
    val title: MutableValue<String>

    fun onSetKeyClick(key: Int)
    fun onBackClicked()
}
