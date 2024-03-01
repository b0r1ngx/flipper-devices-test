package com.example.myapplication.shared.lifecycle

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

abstract class DecomposeViewModel : InstanceKeeper.Instance, LifecycleOwner {
    private val registry by lazy { LifecycleRegistry(Lifecycle.State.INITIALIZED) }
    override val lifecycle = registry

    init {
        registry.onCreate()
        registry.onStart()
        registry.onResume()
    }

//    TODO: add @CallSuper, when using in android
    override fun onDestroy() {
        super.onDestroy()
        registry.onPause()
        registry.onStop()
        registry.onDestroy()
    }
}