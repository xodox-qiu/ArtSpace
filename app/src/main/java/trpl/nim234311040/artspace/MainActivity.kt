package trpl.nim234311040.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trpl.nim234311040.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    // List of artworks
    val artworks = listOf(
        Artwork(". . . And My Rage For All", "Capcom", 2019, R.drawable.myrage),
        Artwork("Point of no return", "Capcom", 2019, R.drawable.reddragon),
        Artwork("The Wrath of Thunder Descends", "Capcom", 2019, R.drawable.thundergod),
        Artwork("A Glance of Silver", "Capcom", 2019, R.drawable.silversol),
        Artwork("The Moon is a Harsh Queen", "Capcom", 2019, R.drawable.goldluna)
    )

    // State to keep track of the current artwork
    var currentIndex by remember { mutableStateOf(0) }

    // Display the artwork at the current index
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtworkDisplay(artwork = artworks[currentIndex])

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if (currentIndex > 0) currentIndex--
            }) {
                Text("Previous")
            }
            Button(onClick = {
                if (currentIndex < artworks.size - 1) currentIndex++
            }) {
                Text("Next")
            }
        }
    }
}

@Composable
fun TextWithBackground(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFECEBF4)) // Set the background color
            .padding(16.dp) // Add padding for spacing
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    // Box to add a frame effect
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(250.dp) // Set a fixed height
            .padding(4.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = artwork.imageResId),
            contentDescription = artwork.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f) // Maintain a specific aspect ratio for consistent framing
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    TextWithBackground(
        text = artwork.title,
    )
    TextWithBackground(
        text = "By ${artwork.artist} (${artwork.year})",
    )
}
