package com.apollo.unscramble.ui.test

import com.apollo.unscramble.data.MAX_NO_OF_WORDS
import com.apollo.unscramble.data.SCORE_INCREASE
import com.apollo.unscramble.data.getUnscrambledWord
import com.apollo.unscramble.ui.GameViewModel
import org.junit.Assert
import org.junit.Test

class GameViewModelTest {

    private val viewModel = GameViewModel()

    // Success case
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdateAndErrorFlagUnset() {
        var gameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        gameUiState = viewModel.uiState.value
        Assert.assertFalse(gameUiState.isGuessedWordWrong)
        Assert.assertEquals(gameUiState.score, SCORE_AFTER_FIRST_CORRECT_ANSWER)
    }

    // Success case
    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var gameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        gameUiState = viewModel.uiState.value
        val lastWordCount = gameUiState.currentWordCount
        viewModel.skipWord()
        gameUiState = viewModel.uiState.value
        Assert.assertEquals(gameUiState.currentWordCount, lastWordCount + 1)
        Assert.assertEquals(gameUiState.score, SCORE_AFTER_FIRST_CORRECT_ANSWER)
    }

    // Error case
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        var gameUiState = viewModel.uiState.value
        val incorrectPlayerWord = "batman"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        gameUiState = viewModel.uiState.value
        Assert.assertTrue(gameUiState.isGuessedWordWrong)
        Assert.assertEquals(gameUiState.score, 0)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }

    // Boundary case
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        Assert.assertNotEquals(gameUiState.currentScrambledWord, unscrambledWord)
        Assert.assertEquals(gameUiState.currentWordCount, 1)
        Assert.assertEquals(gameUiState.score, 0)
        Assert.assertFalse(gameUiState.isGuessedWordWrong)
        Assert.assertFalse(gameUiState.isGameOver)
    }

    // Boundary case
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var gameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)
        repeat(MAX_NO_OF_WORDS) {
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            expectedScore += 20
            gameUiState = viewModel.uiState.value
            Assert.assertEquals(gameUiState.score, expectedScore)
            correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)
        }

        Assert.assertEquals(gameUiState.currentWordCount, MAX_NO_OF_WORDS)
        Assert.assertTrue(gameUiState.isGameOver)
    }

}