package com.example.myapplication.shared.lifecycle

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.example.myapplication.shared.datalayer.InstanceKey

inline fun <reified VM : DecomposeViewModel> ComponentContext.viewModelWithFactoryWithoutRemember(
    key: Any?,
    crossinline factory: () -> VM,
): VM {
    return instanceKeeper.getOrCreate(
        key = InstanceKey(clazz = VM::class, key = key),
        factory = factory,
    )
}