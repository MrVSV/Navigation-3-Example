package com.vsv.navigation3example.presentation.navigation.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import com.vsv.navigation3example.presentation.util.DeviceConfiguration
import com.vsv.navigation3example.presentation.util.currentDeviceConfiguration

class TwoPaneScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(firstEntry, secondEntry)
    override val content: @Composable (() -> Unit) = {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(0.5f)) {
                firstEntry.Content()
            }
            Column(modifier = Modifier.weight(0.5f)) {
                secondEntry.Content()
            }
        }
    }

    companion object {
        internal const val LIST_KEY = "ListDetailScene-List"
        internal const val DETAIL_KEY = "ListDetailScene-Detail"
        fun listPane() = mapOf(LIST_KEY to true)
        fun detailPane() = mapOf(DETAIL_KEY to true)
    }
}

@Composable
fun <T : Any> rememberTwoPaneSceneStrategy(): TwoPaneSceneStrategy<T> {
    val deviceConfiguration = currentDeviceConfiguration()

    return remember(deviceConfiguration) {
        TwoPaneSceneStrategy(deviceConfiguration)
    }
}

class TwoPaneSceneStrategy<T : Any>(
    private val deviceConfiguration: DeviceConfiguration
) : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {

        if (deviceConfiguration in listOf(
                DeviceConfiguration.MOBILE_PORTRAIT,
                DeviceConfiguration.MOBILE_LANDSCAPE,
                DeviceConfiguration.TABLET_PORTRAIT
            )
        ) {
            return null
        }

        val detailEntry = entries.lastOrNull()?.takeIf { it.metadata.containsKey(TwoPaneScene.DETAIL_KEY) } ?: return null
        val listEntry = entries.findLast { it.metadata.containsKey(TwoPaneScene.LIST_KEY) } ?: return null
        val sceneKey = Pair(listEntry, detailEntry)
        return TwoPaneScene(
            key = sceneKey,
            previousEntries = entries.dropLast(2),
            firstEntry = listEntry,
            secondEntry = detailEntry
        )
    }
}

