package com.apollo.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
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
        modifier = modifier.testTag("DogList")
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
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
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
                Spacer(modifier = Modifier.weight(1f))
                DogCardButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                DogHobby(
                    hobby = dog.hobby,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_medium)
                        )
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

@Composable
fun DogCardButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (!expanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DogHobby(
    @StringRes hobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(id = hobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
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