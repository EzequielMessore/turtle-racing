package br.com.messore.tech.turtleracing.domain.usecase.token

import javax.inject.Inject

class PrinterUseCase @Inject constructor() {

    operator fun invoke(print: String): String {
        return "$javaClass => $print"
    }
}