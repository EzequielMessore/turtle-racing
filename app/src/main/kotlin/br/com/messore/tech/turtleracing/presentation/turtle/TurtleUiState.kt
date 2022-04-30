package br.com.messore.tech.turtleracing.presentation.turtle

import br.com.messore.tech.turtleracing.domain.model.Turtle

data class TurtleUiState(
    val isLoading: Boolean = false,
    val turtles: List<Turtle> = emptyList()
)
