package com.apollo.diceroller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apollo.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceRollerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DiceRollerApp(modifier: Modifier = Modifier) {
    DiceWithImageAndButton(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithImageAndButton(modifier: Modifier = Modifier) {
    var result: Int by remember {
        mutableIntStateOf(1)
    }
    val (imageResource, imageDesc) = when(result) {
        1 -> Pair(R.drawable.dice_1, stringResource(R.string.dice_1))
        2 -> Pair(R.drawable.dice_2, stringResource(R.string.dice_2))
        3 -> Pair(R.drawable.dice_3, stringResource(R.string.dice_3))
        4 -> Pair(R.drawable.dice_4, stringResource(R.string.dice_4))
        5 -> Pair(R.drawable.dice_5, stringResource(R.string.dice_5))
        else -> Pair(R.drawable.dice_6, stringResource(R.string.dice_6))
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = imageDesc
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = (1..6).random()
                Log.v("Dice Roller App", "User rolled $result on the dice.")
            }
        ) {
            Text(
                text = stringResource(R.string.roll)
            )
        }
    }
}