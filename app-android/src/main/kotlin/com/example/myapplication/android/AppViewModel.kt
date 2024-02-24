package com.example.myapplication.android

import androidx.lifecycle.ViewModel
import com.example.myapplication.shared.Repository

class AppViewModel(
    val repository: Repository = Repository()
): ViewModel() {

}