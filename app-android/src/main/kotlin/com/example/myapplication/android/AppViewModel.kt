package com.example.myapplication.android

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.shared.Repository
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum

// TODO: Not used in current project-world
class AppViewModel(
    repository: Repository = Repository()
) : ViewModel() {
    val repository = mutableStateOf(repository)

    fun lockerSetKey(tabEnum: BottomBarTabEnum) {
        val map = repository.value.lockerData.find {
            it.first == tabEnum
        }!!.second
    }
}