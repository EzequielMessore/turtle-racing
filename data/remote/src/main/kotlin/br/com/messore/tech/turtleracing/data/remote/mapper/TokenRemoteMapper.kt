package br.com.messore.tech.turtleracing.data.remote.mapper

import br.com.messore.tech.turtleracing.domain.model.Token as TokenDomain
import br.com.messore.tech.turtleracing.data.remote.model.Token as TokenRemote

fun TokenRemote.toDomain() = TokenDomain(
    token = token
)
