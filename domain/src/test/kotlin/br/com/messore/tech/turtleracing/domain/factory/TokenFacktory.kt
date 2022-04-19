package br.com.messore.tech.turtleracing.domain.factory

import br.com.messore.tech.turtleracing.domain.model.Token

object TokenFacktory {
    fun getToken() = Token("73fabf32-27b4-469b-8f06-f2ef600fa4fc")
    fun getEmptyToken() = Token("")
}
