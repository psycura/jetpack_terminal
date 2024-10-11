package com.example.terminal.presentation

import com.example.terminal.data.models.Bar

sealed class TerminalScreenState {
    data object Initial : TerminalScreenState()
    data object Loading : TerminalScreenState()
    data class Content(
        val bars: List<Bar>,
        val timeFrame: TimeFrame,
    ) : TerminalScreenState()
}