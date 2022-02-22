package br.com.messore.tech.turtleracing.data.remote.extensions

import br.com.messore.tech.turtleracing.data.remote.AuthenticationException
import br.com.messore.tech.turtleracing.data.remote.UnexpectedException
import java.net.HttpURLConnection
import retrofit2.HttpException as RetrofitHttpException

internal fun <T> Result<T>.getOrThrowDomainError(): T = getOrElse { throwable ->
    throw throwable.toDomainError()
}

internal fun Throwable.toDomainError(): Throwable {
    return when (this) {
        is RetrofitHttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> AuthenticationException
                else -> UnexpectedException(message())
            }
        }
        else -> this
    }
}
