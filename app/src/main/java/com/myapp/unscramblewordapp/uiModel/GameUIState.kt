package com.myapp.unscramblewordapp.uiModel

data class GameUIState(
    val currentScrambledWord:String = "",
    val isGuessedWordWrong:Boolean = false
)
