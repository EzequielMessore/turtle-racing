package br.com.messore.tech.turtleracing.presentation.turtle.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import br.com.messore.tech.turtleracing.ui.theme.SPACING_1
import br.com.messore.tech.turtleracing.ui.theme.SPACING_2
import br.com.messore.tech.turtleracing.ui.theme.TurtleRacingTheme
import coil.compose.AsyncImage
import java.time.LocalTime

@Composable
@ExperimentalFoundationApi
internal fun TurtleScreen(
    turtles: List<Turtle>,
    loading: Boolean = true,
    onClick: () -> Unit = {},
) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(turtles) { turtle ->
            Turtle(turtle = turtle)
        }
    }
}

@Composable
private fun Turtle(turtle: Turtle) {
    Box(modifier = Modifier.padding(SPACING_2)) {
        Card(
            modifier = Modifier.height(180.dp),
            shape = RoundedCornerShape(SPACING_2),
            elevation = SPACING_2
        ) {
            Column(
                Modifier
                    .padding(SPACING_2)
                    .align(Alignment.Center)
            ) {
                TurtleImage()

                Spacer(modifier = Modifier.size(SPACING_1))

                TurtleInfo(turtle)
            }
        }
    }
}

@Composable
private fun TurtleImage() {
    AsyncImage(
        model = "https://play.turtleracing.io/img/SKIN_VEGETA.png",
        contentDescription = null,
        modifier = Modifier.clip(CircleShape),
    )
}

@Composable
private fun TurtleInfo(turtle: Turtle) {
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Column {
            Text(text = "Runs")
            Text(text = turtle.timer.toString())
        }

        Spacer(modifier = Modifier.size(16.dp))

        Column {
            Text(text = "Energy")
            Text(text = turtle.energy.toString())
        }
    }
}

@Composable
@ExperimentalFoundationApi
@Preview(showBackground = true)
private fun TurtleListPreview() = TurtleRacingTheme {
    val turtle = Turtle(
        id = "id",
        type = TurtleType.COMMON,
        age = 25,
        energy = 65,
        run = 4,
        timer = LocalTime.of(8, 12, 34),
        visibleId = "1234"
    )

    TurtleScreen(listOf(turtle))
}
