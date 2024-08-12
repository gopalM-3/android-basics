package com.apollo.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollo.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color(0xFFA4C639))
            .fillMaxSize()
    ) {
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        CardBody()
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        CardFooter()
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}

@Composable
fun CardBody(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        val icon = painterResource(id = R.drawable.baseline_account_box_24)
        Icon(
            painter = icon,
            contentDescription = "Phone icon.",
            modifier = modifier.size(150.dp)
        )
        Text(
            text = stringResource(R.string.cardholder_name),
            fontSize = 50.sp,
            modifier = modifier
        )
        Text(
            text = stringResource(R.string.cardholder_designation),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun CardFooter(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Row(
            modifier = modifier.padding(8.dp)
        ) {
            val icon = painterResource(id = R.drawable.baseline_local_phone_24)
            Icon(
                painter = icon,
                contentDescription = "Phone icon.",
                modifier = modifier.padding(end = 24.dp)
            )
            Text(
                text = stringResource(R.string.ph_no),
                modifier = modifier
            )
        }
        Row(
            modifier = modifier.padding(
                    start = 8.dp,
                    end = 8.dp
                )
        ) {
            val icon = painterResource(id = R.drawable.baseline_alternate_email_24)
            Icon(
                painter = icon,
                contentDescription = "Social media handle icon.",
                modifier = modifier.padding(end = 24.dp)
            )
            Text(
                text = stringResource(R.string.social_media_handle),
                modifier = modifier
            )
        }
        Row(
            modifier = modifier.padding(8.dp)
        ) {
            val icon = painterResource(id = R.drawable.baseline_email_24)
            Icon(
                painter = icon,
                contentDescription = "Email icon.",
                modifier = modifier.padding(end = 24.dp)
            )
            Text(
                text = stringResource(R.string.email_id),
                modifier = modifier
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}