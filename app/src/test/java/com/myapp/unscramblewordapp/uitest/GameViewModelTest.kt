package com.myapp.unscramblewordapp.uitest

import androidx.lifecycle.ViewModel
import com.myapp.unscramblewordapp.data.MAX_Number
import com.myapp.unscramblewordapp.data.SCORE_Increase
import com.myapp.unscramblewordapp.data.getUnscrambledWord
import com.myapp.unscramblewordapp.uiModel.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdateAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

     @Test
     fun gameViewModel_IncorrectGuess_ErrorFlagSet(){
         val incorrectPlayWord = "and"
         viewModel.updateUserGuess(incorrectPlayWord)
         viewModel.checkUserGuess()
         val currentGameUIState = viewModel.uiState.value
         assertEquals(0,currentGameUIState.score)
         assertTrue(currentGameUIState.isGuessedWordWrong)
     }

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded(){
        val gameUIState = viewModel.uiState.value
        val unscrambledWord = getUnscrambledWord(gameUIState.currentScrambledWord)
        assertNotEquals(unscrambledWord, gameUIState.currentScrambledWord)
        assertTrue(gameUIState.currentWordCount == 1)
        assertTrue(gameUIState.score==0)
        assertFalse(gameUIState.isGuessedWordWrong)
        assertFalse(gameUIState.isGameOver)
    }
    @Test
    fun gameViewModel_AllWordGuessed_UIStateUpdateCorrectly(){
        var expectedScore =0
        var currentGameUIState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUIState.currentScrambledWord)
        repeat(MAX_Number){
            expectedScore += SCORE_Increase
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUIState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUIState.currentScrambledWord)
            assertEquals(expectedScore, currentGameUIState.score)
            }
        assertEquals(MAX_Number,currentGameUIState.currentWordCount)
        assertTrue(currentGameUIState.isGameOver)

    }
    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased(){
        var currentGameUIState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUIState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUIState = viewModel.uiState.value
        val lastWordCount = currentGameUIState.currentWordCount
        viewModel.skipWord()
        currentGameUIState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER,currentGameUIState.score)
        assertEquals(lastWordCount+1,currentGameUIState.currentWordCount)
    }


    companion object{
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_Increase
    }
}