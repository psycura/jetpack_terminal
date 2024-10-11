package com.example.terminal.presentation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.terminal.data.models.Bar
import kotlinx.parcelize.Parcelize
import kotlin.math.roundToInt

@Parcelize
data class TerminalState(
    val visibleBarsCount: Int = 100,
    val terminalWidth: Float = 1f,
    val terminalHeight: Float = 1f,
    val bars: List<Bar>,
    val scrolledBy: Float = 0f
) : Parcelable {
    val barWidth: Float
        get() = terminalWidth / visibleBarsCount

    private val visibleBars: List<Bar>
        get() {
            val startIndex = (scrolledBy / barWidth).roundToInt().coerceAtLeast(0)
            val endIndex = (startIndex + visibleBarsCount).coerceAtMost(bars.size)

            return bars.subList(startIndex, endIndex)
        }

    val max: Float
        get() = visibleBars.maxOf { it.high }

    val min: Float
        get() = visibleBars.minOf { it.low }

    val pxPerPoint: Float
        get() = terminalHeight / (max - min)
}


@Composable
fun rememberTerminalState(bars: List<Bar>): MutableState<TerminalState> {
    return rememberSaveable {
        mutableStateOf(
            TerminalState(bars = bars)
        )
    }
}