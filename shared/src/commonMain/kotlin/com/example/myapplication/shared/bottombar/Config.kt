package com.example.myapplication.shared.bottombar

import kotlinx.serialization.Serializable

@Serializable
sealed interface Config {
    val enum: BottomBarTabEnum

    @Serializable
    data object Hope : Config {
        override val enum: BottomBarTabEnum
            get() = BottomBarTabEnum.hope
    }

    @Serializable
    data object WeHave : Config {
        override val enum: BottomBarTabEnum
            get() = BottomBarTabEnum.weHave
    }

    @Serializable
    data object AChance : Config {
        override val enum: BottomBarTabEnum
            get() = BottomBarTabEnum.aChance
    }

    @Serializable
    data object Hidden : Config {
        override val enum: BottomBarTabEnum
            get() = BottomBarTabEnum.hidden
    }
}