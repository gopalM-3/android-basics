package com.apollo.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apollo.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuadrantComposable(
                        title1 = stringResource(R.string.title_quad1),
                        body1 = stringResource(R.string.body_quad1),
                        color1 = Color(0xFFEADDFF),
                        title2 = stringResource(R.string.title_quad2),
                        body2 = stringResource(R.string.body_quad2),
                        color2 = Color(0xFFD0BCFF),
                        title3 = stringResource(R.string.title_quad3),
                        body3 = stringResource(R.string.body_quad3),
                        color3 = Color(0xFFB69DF8),
                        title4 = stringResource(R.string.title_quad4),
                        body4 = stringResource(R.string.body_quad4),
                        color4 = Color(0xFFF6EDFF),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuadrantComposable(
    title1: String, body1: String, color1: Color,
    title2: String, body2: String, color2: Color,
    title3: String, body3: String, color3: Color,
    title4: String, body4: String, color4: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        RowComposable(
            title1 = title1,
            body1 = body1,
            color1 = color1,
            title2 = title2,
            body2 = body2,
            color2 = color2,
            modifier = modifier.weight(1f)
        )
        RowComposable(
            title1 = title3,
            body1 = body3,
            color1 = color3,
            title2 = title4,
            body2 = body4,
            color2 = color4,
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
fun RowComposable(
    title1: String, body1: String, color1: Color,
    title2: String, body2: String, color2: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TextComposable(
            title = title1,
            body = body1,
            color = color1,
            modifier = modifier.wrapContentSize()
        )
        TextComposable(
            title = title2,
            body = body2,
            color = color2,
            modifier = modifier.wrapContentSize()
        )
    }
}

@Composable
fun TextComposable(title: String, body: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(color)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = body,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun QuadrantComposablePreview() {
    QuadrantComposable(
        title1 = stringResource(R.string.title_quad1),
        body1 = stringResource(R.string.body_quad1),
        color1 = Color(0xFFEADDFF),
        title2 = stringResource(R.string.title_quad2),
        body2 = stringResource(R.string.body_quad2),
        color2 = Color(0xFFD0BCFF),
        title3 = stringResource(R.string.title_quad3),
        body3 = stringResource(R.string.body_quad3),
        color3 = Color(0xFFB69DF8),
        title4 = stringResource(R.string.title_quad4),
        body4 = stringResource(R.string.body_quad4),
        color4 = Color(0xFFF6EDFF)
    )
}