package com.example.myapplication.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.myapplication.main.TabContent
import com.example.myapplication.shared.Repository
import com.example.myapplication.shared.`bottom-bar`.BottomBarTabEnum
import com.example.myapplication.shared.root.RootComponent
import com.example.myapplication.shared.root.RootComponent.Child
import com.example.myapplication.welcome.LockerSetKeyContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
    repository: Repository = Repository()
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Children(
                stack = component.stack,
//                modifier = Modifier.fillMaxSize(),
//                animation = stackAnimation(
//                    fade() // + scale()
//                )
            ) {
                val childStack by component.stack.subscribeAsState()

                when (val instance = it.instance) {
                    is Child.Hope ->
                        TabContent(
                            tab = BottomBarTabEnum.hope,
                            component = instance.component,
                            childStack = childStack,
                            lockers = repository.lockerData.find {
                                it.first == BottomBarTabEnum.hope
                            }!!.second,
                        )

                    is Child.WeHave -> TabContent(
                        tab = BottomBarTabEnum.weHave,
                        component = instance.component,
                        childStack = childStack,
                        lockers = repository.lockerData.find {
                            it.first == BottomBarTabEnum.weHave
                        }!!.second,
                    )

                    is Child.AChance ->
                        TabContent(
                            tab = BottomBarTabEnum.aChance,
                            component = instance.component,
                            childStack = childStack,
                            lockers = repository.lockerData.find {
                                it.first == BottomBarTabEnum.aChance
                            }!!.second,
                        )

                    is Child.LockerSetKey -> LockerSetKeyContent(
                        component = instance.component
                    )
                }
            }
        }
    }
}
