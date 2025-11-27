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
        internal const val TWO_PANE_KEY = "TwoPane"
        fun twoPane() = mapOf(TWO_PANE_KEY to true)
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

        val lastTwoEntries = entries.takeLast(2)

        return if (lastTwoEntries.size == 2
            && lastTwoEntries.all { it.metadata.containsKey(TwoPaneScene.TWO_PANE_KEY) }
        ) {
            val firstEntry = lastTwoEntries.first()
            val secondEntry = lastTwoEntries.last()

            val sceneKey = Pair(firstEntry.contentKey, secondEntry.contentKey)

            TwoPaneScene(
                key = sceneKey,
                previousEntries = entries.dropLast(1),
                firstEntry = firstEntry,
                secondEntry = secondEntry
            )
        } else {
            null
        }
    }
}

