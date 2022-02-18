package br.com.messore.tech.turtleracing.presentation.login

sealed class LoginUiAction {
    object Logged : LoginUiAction()
    data class ErrorOnLogin(val message: String) : LoginUiAction()
}
