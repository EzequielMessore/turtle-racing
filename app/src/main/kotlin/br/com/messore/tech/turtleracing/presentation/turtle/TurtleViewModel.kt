package br.com.messore.tech.turtleracing.presentation.turtle

import androidx.lifecycle.viewModelScope
import br.com.messore.tech.turtleracing.core.BaseViewModel
import br.com.messore.tech.turtleracing.core.runCatchingWithDispatcher
import br.com.messore.tech.turtleracing.di.CoroutineDispatcherDefault
import br.com.messore.tech.turtleracing.domain.usecase.runner.ScheduleRunnersUseCase
import br.com.messore.tech.turtleracing.domain.usecase.turtle.ListTurtlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurtleViewModel @Inject constructor(
    private val listTurtlesUseCase: ListTurtlesUseCase,
    private val scheduleRunnersUseCase: ScheduleRunnersUseCase,
    @CoroutineDispatcherDefault private val dispatcher: CoroutineDispatcher
) : BaseViewModel<TurtleUiState, TurtleUiAction>(TurtleUiState()) {

    fun startRunners() = viewModelScope.launch {
        runCatchingWithDispatcher(
            dispatcher = dispatcher,
            execute = {
                scheduleRunnersUseCase()
            }
        )
    }

    fun getTurtles() = viewModelScope.launch {
        setState { copy(isLoading = true) }
        runCatchingWithDispatcher(
            dispatcher = dispatcher,
            execute = {
                listTurtlesUseCase()
            },
            onSuccess = { turtles ->
                setState {
                    copy(isLoading = false, turtles = turtles)
                }
            }
        )
    }
}
