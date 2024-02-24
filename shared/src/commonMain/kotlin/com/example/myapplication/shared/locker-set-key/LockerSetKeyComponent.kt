package com.example.myapplication.shared.`locker-set-key`

import com.arkivanov.decompose.value.MutableValue

interface LockerSetKeyComponent {
    val title: MutableValue<String>

    fun onSetKeyClick(key: Int)
    fun onBackClicked()
}
