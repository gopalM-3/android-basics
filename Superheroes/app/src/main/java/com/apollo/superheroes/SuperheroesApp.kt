package com.apollo.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apollo.superheroes.model.Hero
import com.apollo.superheroes.repository.HeroesRepository
import com.apollo.superheroes.ui.theme.SuperheroesTheme

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SuperheroesAppLightPreview() {
    SuperheroesTheme(darkTheme = false) {
        SuperheroesApp()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SuperheroesAppDarkPreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold(
        topBar = { SuperheroesAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ListOfSuperheroes(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .statusBarsPadding()
                .safeDrawingPadding()
                .safeContentPadding()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}

@Composable
fun ListOfSuperheroes(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(HeroesRepository.heroes) { hero ->
            SuperheroCard(hero = hero)
        }
    }
}

@Composable
fun SuperheroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier.padding(
            top = 8.dp,
            bottom = 8.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .height(72.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(
                    R.string.image_of_hero,
                    stringResource(
                        id = hero.nameRes
                    )
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(72.dp)
                    .weight(1f)
                    .clip(shape = MaterialTheme.shapes.small)
            )
        }
    }
}