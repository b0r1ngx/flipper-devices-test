package com.example.myapplication.shared.model

import kotlin.reflect.KClass

data class InstanceKey(
    val clazz: KClass<*>,
    val key: Any?
)
