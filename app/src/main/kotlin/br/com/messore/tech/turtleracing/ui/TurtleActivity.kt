package br.com.messore.tech.turtleracing.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.messore.tech.turtleracing.presentation.turtle.TurtleViewModel
import br.com.messore.tech.turtleracing.ui.theme.TurtleRacingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TurtleActivity : ComponentActivity() {

    private val viewModel by viewModels<TurtleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TurtleRacingTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        viewModel.startRunners()
                    }) {
                        Text(text = "Automatic run")
                    }
                }
            }
        }
    }
}
