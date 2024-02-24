package com.example.myapplication.shared.`bottom-bar`

import kotlinx.serialization.Serializable

@Serializable
enum class BottomBarTabEnum(
    val secondName: String
) {
    hope("Hope"),
    weHave("We have"),
    aChance("A chance"),
    hidden("hidden");

    fun toConfig(): Config = when (this) {
        hope -> Config.Hope
        weHave -> Config.WeHave
        aChance -> Config.AChance
        hidden -> Config.Hidden
    }
}
