package com.apollo.unscramble.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apollo.unscramble.R
import com.apollo.unscramble.ui.theme.UnscrambleTheme

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UnscrambleLightPreview(modifier: Modifier = Modifier) {
    UnscrambleTheme(darkTheme = false) {
        UnscrambleApp()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UnscrambleDarkPreview(modifier: Modifier = Modifier) {
    UnscrambleTheme(darkTheme = true) {
        UnscrambleApp()
    }
}

@Composable
fun UnscrambleApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        GameUi(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .statusBarsPadding()
                .safeContentPadding()
                .safeDrawingPadding()
                .wrapContentSize(align = Alignment.Center)
        )
    }
}

@Composable
fun GameUi(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        GameLayout(
            currentScrambledWord = gameUiState.currentScrambledWord,
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            wordCount = gameUiState.currentWordCount,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
        Column(
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(id = R.dimen.padding_medium)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { gameViewModel.checkUserGuess() }
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = { gameViewModel.skipWord() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.skip),
                    fontSize = 16.sp
                )
            }
        }
        if (gameUiState.isGameOver) {
            FinalScoreCard(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
        ScoreCard(
            score = gameUiState.score,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    isGuessWrong: Boolean,
    userGuess: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    wordCount: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(id = R.dimen.padding_medium)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(id = R.string.word_count, wordCount),
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.onPrimary,
                modifier = Modifier
                    .clip(shapes.medium)
                    .background(colorScheme.surfaceTint)
                    .padding(
                        horizontal = 10.dp,
                        vertical = 4.dp
                    )
                    .align(alignment = Alignment.End)
            )
            Text(
                text = currentScrambledWord,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(id = R.string.instructions),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = userGuess,
                onValueChange = onUserGuessChanged,
                singleLine = true,
                shape = shapes.large,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface
                ),
                label = {
                    if (isGuessWrong) {
                        Text(text = stringResource(id = R.string.wrong_guess))
                    } else {
                        Text(text = stringResource(id = R.string.enter_your_word))
                    }
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() }
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ScoreCard(
    score: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun FinalScoreCard(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)
    
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.play_again))
            }
        },
        modifier = modifier
    )
}