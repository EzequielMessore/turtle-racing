package br.com.messore.tech.turtleracing.presentation.login

import androidx.lifecycle.viewModelScope
import br.com.messore.tech.turtleracing.core.BaseViewModel
import br.com.messore.tech.turtleracing.core.runCatchingWithDispatcher
import br.com.messore.tech.turtleracing.di.CoroutineDispatcherDefault
import br.com.messore.tech.turtleracing.domain.usecase.token.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    @CoroutineDispatcherDefault private val dispatcher: CoroutineDispatcher
) : BaseViewModel<LoginUiState, LoginUiAction>(LoginUiState()) {

    fun login() = viewModelScope.launch {
        runCatchingWithDispatcher(
            dispatcher = dispatcher,
            execute = {
                getTokenUseCase()
            },
            onSuccess = {},
            onFailure = {},
        )
    }
}
