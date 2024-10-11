package com.example.terminal.data.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.Date

@Immutable
@Parcelize
@Serializable
data class Bar(
    @SerialName("o") val open: Float,
    @SerialName("c") val close: Float,
    @SerialName("l") val low: Float,
    @SerialName("h") val high: Float,
    @SerialName("t") val time: Long,
): Parcelable {
    val calendar: Calendar
        get() {
            return Calendar.getInstance().apply {
                time = Date(this@Bar.time)
            }
        }
}
