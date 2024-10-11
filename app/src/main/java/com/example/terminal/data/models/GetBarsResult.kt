package com.example.terminal.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBarsResult(
    @SerialName("results") val barList: List<Bar>
)
