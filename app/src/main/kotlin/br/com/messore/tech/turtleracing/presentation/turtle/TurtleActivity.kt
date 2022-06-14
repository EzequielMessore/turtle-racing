package br.com.messore.tech.turtleracing.presentation.turtle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.com.messore.tech.turtleracing.presentation.turtle.compose.TurtleScreen
import br.com.messore.tech.turtleracing.ui.theme.TurtleRacingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalFoundationApi
class TurtleActivity : ComponentActivity() {

    private val viewModel by viewModels<TurtleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startRunners()
        viewModel.getTurtles()

        setContent {
            TurtleRacingTheme {
                val state by viewModel.state.collectAsState()

                TurtleScreen(
                    turtles = state.turtles
                )
            }
        }
    }
}
