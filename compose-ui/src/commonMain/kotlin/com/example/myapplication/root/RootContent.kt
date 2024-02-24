package com.example.myapplication.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.myapplication.main.TabContent
import com.example.myapplication.shared.root.RootComponent
import com.example.myapplication.shared.root.RootComponent.Child
import com.example.myapplication.welcome.LockerSetKeyContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        Surface(
            modifier = modifier
                .fillMaxSize()
        ) {
            Children(
                stack = component.stack,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(
                    fade() // + scale()
                )
            ) {
                val childStack by component.stack.subscribeAsState()

                when (val instance = it.instance) {
                    is Child.Hope -> TabContent(
                        component = instance.component,
                        childStack = childStack
                    )

                    is Child.LockerSetKey -> LockerSetKeyContent(
                        component = instance.component
                    )

                    is Child.AChance -> TODO()
                    is Child.WeHave -> TODO()
                }
            }
        }
    }
}
