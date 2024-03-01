package com.example.myapplication.shared.lockersetkey

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.example.myapplication.shared.datalayer.Repository
import com.example.myapplication.shared.bottombar.BottomBarTabEnum

class DefaultLockerSetKeyComponent(
    private val componentContext: ComponentContext,
    private val tabEnum: MutableValue<BottomBarTabEnum>,
    private val locker: MutableValue<Int>,
    private val repository: Repository,
    private val onFinished: () -> Unit,
) : LockerSetKeyComponent, ComponentContext by componentContext {
    override val title = MutableValue(
        "Selecting a key for the locker #${locker.value}"
    )

    override fun onSetKeyClick(key: Int) {
        repository
            .lockerData[tabEnum.value.ordinal]
            .second[locker.value] = key
        onBackClicked()
    }

    override fun onBackClicked() {
        onFinished()
    }
}
