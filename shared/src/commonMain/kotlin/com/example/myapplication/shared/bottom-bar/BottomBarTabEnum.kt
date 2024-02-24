package com.example.myapplication.shared.`bottom-bar`

enum class BottomBarTabEnum(
    val secondName: String
) {
    hope("Hope"),
    weHave("We have"),
    aChance("A chance");

    fun toConfig(): Config = when (this) {
        hope -> Config.Hope
        weHave -> Config.WeHave
        aChance -> Config.AChance
    }
}
