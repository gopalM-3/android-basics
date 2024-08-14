package com.apollo.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollo.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
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
fun ArtSpaceApp(modifier: Modifier = Modifier) {
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
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        ImageWithDescAndButtons(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun ImageWithDescAndButtons(modifier: Modifier = Modifier) {
    var imageId by remember {
        mutableIntStateOf(1)
    }

    class Image(
        val imageResource: Int,
        val imageName: String,
        val imageDesc: String,
        val imageYear: String
    )

    val image: Image = when(imageId) {
        1 -> {
            Image(
                imageResource = R.drawable.cutedogs,
                imageName = stringResource(R.string.cute_dogs),
                imageDesc = stringResource(R.string.cute_minecraft_dogs),
                imageYear = stringResource(R.string.img_1_year)
            )
        }
        2 -> {
            Image(
                imageResource = R.drawable.cozy_drive,
                imageName = stringResource(R.string.cozy_drive),
                imageDesc = stringResource(R.string.cozy_drive_in_a_nissan),
                imageYear = stringResource(R.string.img_2_year)
            )
        }
        3 -> {
            Image(
                imageResource = R.drawable.seb,
                imageName = stringResource(R.string.sebastian_vettel),
                imageDesc = stringResource(R.string.seb),
                imageYear = stringResource(R.string.img_3_year)
            )
        }
        4 -> {
            Image(
                imageResource = R.drawable.leap_of_faith,
                imageName = stringResource(R.string.miles_morales),
                imageDesc = stringResource(R.string.miles_jumps),
                imageYear = stringResource(R.string.img_4_year)
            )
        }
        5 -> {
            Image(
                imageResource = R.drawable.pink_clouds,
                imageName = stringResource(R.string.sakura_trees),
                imageDesc = stringResource(R.string.scenic_view),
                imageYear = stringResource(R.string.img_5_year)
            )
        }
        else -> {
            Image(
                imageResource = R.drawable.fsamurai_saturated,
                imageName = stringResource(R.string.a_samurai),
                imageDesc = stringResource(R.string.we_fight_we_win),
                imageYear = stringResource(R.string.img_6_year)
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
    ) {
        ImageWall(
            imageResource = image.imageResource,
            modifier = Modifier.fillMaxHeight(0.66f)
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.24f))
        ImageDescription(
            imageName = image.imageName,
            imageDesc = image.imageDesc,
            imageYear = image.imageYear
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.24f))
        RowOfButtons(
            prevEnabled = imageId != 1,
            prevLambda = {
                if (imageId in 2..6) {
                    imageId--
                }
            },
            nextEnabled = imageId != 6,
            nextLambda = {
                if (imageId in 1..5) {
                    imageId++
                }
            }
        )
    }
}

@Composable
fun ImageWall(
    @DrawableRes imageResource: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 15.dp,
        shape = RoundedCornerShape(10)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            modifier = modifier
                .padding(32.dp)
        )
    }
}

@Composable
fun ImageDescription(
    imageName: String,
    imageDesc: String,
    imageYear: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(25),
        modifier = modifier
    ) {
        Column(
            modifier = modifier.padding(20.dp)
        ) {
            Text(
                text = imageName,
                fontSize = 20.sp
            )
            Column {
                Text(
                    text = imageDesc,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "($imageYear)")
            }
        }
    }
}

@Composable
fun RowOfButtons(
    prevEnabled: Boolean,
    prevLambda: () -> Unit,
    nextEnabled: Boolean,
    nextLambda: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = prevLambda,
            enabled = prevEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            ),
            modifier = modifier.width(136.dp)
        ) {
            Text(text = stringResource(R.string.previous))
        }
        Button(
            onClick = nextLambda,
            enabled = nextEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            ),
            modifier = modifier.width(136.dp)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}