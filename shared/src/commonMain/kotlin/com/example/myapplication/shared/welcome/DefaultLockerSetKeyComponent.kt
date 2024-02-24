package com.example.myapplication.shared.welcome

import com.arkivanov.decompose.ComponentContext
import com.example.myapplication.shared.Repository
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

class DefaultLockerSetKeyComponent(
    private val componentContext: ComponentContext,
    private val tabEnum: BottomBarTabEnum,
    private val locker: Int,
    private val repository: Repository,
    private val onFinished: () -> Unit,
) : LockerSetKeyComponent, ComponentContext by componentContext {
    override val title: String
        get() = "Selecting a key for the locker #$locker"

    // TODO: try to remove all !!, everywhere
    override fun onSetKeyClick(key: Int) {
        repository.lockerData.find {
            it.first == tabEnum
        }!!.second[locker] = key
        onBackClicked()
    }

    override fun onBackClicked() {
        onFinished()
    }
}
