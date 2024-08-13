package com.apollo.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollo.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEBE393),
                    titleContentColor = Color(0xFF292720)
                )
            )
        }
    ) { innerPadding ->
        ButtonWithDescription(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .background(color = Color(0xFFFEFCFE))
        )
    }
}

@Composable
fun ButtonWithDescription(modifier: Modifier = Modifier) {
    var buttonState: Int by remember {
        mutableIntStateOf(1)
    }
    val (imageResource, imageDesc, buttonDesc) = when(buttonState) {
        1 -> Triple(
            R.drawable.lemon_tree,
            stringResource(R.string.lemon_tree),
            stringResource(R.string.step_1)
        )
        2 -> Triple(
            R.drawable.lemon_squeeze,
            stringResource(R.string.cut_lemon),
            stringResource(R.string.step_2)
        )
        3 -> Triple(
            R.drawable.lemon_drink,
            stringResource(R.string.lemonade),
            stringResource(R.string.step_3)
        )
        else -> Triple(
            R.drawable.lemon_restart,
            stringResource(R.string.empty_glass),
            stringResource(R.string.step_4)
        )
    }
    var squeezeCount: Int = (2..4).random()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (buttonState in 1..3) {
                    if (buttonState == 2) {
                        if (squeezeCount > 1) {
                            squeezeCount--
                        } else {
                            buttonState++
                        }
                    } else {
                        buttonState++
                    }
                } else {
                    buttonState = 1
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD4E4DC)
            ),
            shape = RoundedCornerShape(20)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = imageDesc
            )
        }
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Text(
            text = buttonDesc,
            fontSize = 18.sp
        )
    }
}