package com.example.myapplication.shared.datalayer

import kotlin.reflect.KClass

data class InstanceKey(
    val clazz: KClass<*>,
    val key: Any?
)
