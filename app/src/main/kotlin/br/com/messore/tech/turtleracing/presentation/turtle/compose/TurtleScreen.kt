package br.com.messore.tech.turtleracing.presentation.turtle.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.messore.tech.turtleracing.domain.extensions.formatTime
import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import br.com.messore.tech.turtleracing.presentation.turtle.ClockViewModel
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
    val countTimeViewModel: ClockViewModel = viewModel()
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(turtles) { turtle ->
            Turtle(turtle = turtle, viewModel = countTimeViewModel)
        }
    }
}

@Composable
private fun Turtle(turtle: Turtle, viewModel: ClockViewModel) {
    Box(modifier = Modifier.padding(SPACING_2)) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(SPACING_2),
            elevation = SPACING_2
        ) {
            Column(
                Modifier
                    .padding(SPACING_2)
                    .align(Alignment.Center)
            ) {
                TurtleImage(turtle.image)

                Spacer(modifier = Modifier.size(SPACING_1))

                val time by viewModel.timeMillis.collectAsState()
                TurtleInfo(turtle, time)
            }
        }
    }
}

@Composable
private fun TurtleImage(image: String?) {
    AsyncImage(
        model = image,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun TurtleInfo(turtle: Turtle, time: Long) {
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Column {
            Text(text = "Runs")
            val expiration = turtle.expirationTime - time
            Text(text = if (expiration >= 0) expiration.formatTime() else "Running")
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
        visibleId = "1234",
        expirationTime = 1
    )

    TurtleScreen(listOf(turtle))
}
