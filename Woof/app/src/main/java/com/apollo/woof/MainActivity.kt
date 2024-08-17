package com.apollo.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.apollo.woof.data.Dog
import com.apollo.woof.data.dogs
import com.apollo.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofTheme {
                WoofApp()
            }
        }
    }
}

@Composable
fun WoofApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { WoofTopBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ListOfDogCards(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .statusBarsPadding()
                .safeDrawingPadding()
        )
    }
}

@Composable
fun ListOfDogCards(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(dogs) {
            DogCard(
                dog = it,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun DogCard(
    dog: Dog,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = dog.photo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size))
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clip(MaterialTheme.shapes.small)
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = dog.name),
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = stringResource(id = R.string.years_old, dog.age),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Light mode"
)
@Composable
fun WoofAppPreview() {
    WoofTheme {
        WoofApp()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dark mode"
)
@Composable
fun WoofAppDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}