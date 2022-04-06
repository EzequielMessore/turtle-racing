package br.com.messore.tech.turtleracing.presentation.turtle

sealed class TurtleUiAction {
    object Logged : TurtleUiAction()
    data class ErrorOnLogin(val message: String) : TurtleUiAction()
}
