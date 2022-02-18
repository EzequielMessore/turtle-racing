package br.com.messore.tech.turtleracing.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun <T, R> T.runCatchingWithDispatcher(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> R,
    onSuccess: (R) -> Unit,
    onFailure: (Throwable) -> Unit = {}
) {
    runCatching {
        withContext(dispatcher) {
            execute()
        }
    }.onSuccess(onSuccess)
        .onFailure {
            Timber.e(it)
            onFailure(it)
        }
}