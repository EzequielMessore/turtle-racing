package br.com.messore.tech.turtleracing.data.remote.mapper

import br.com.messore.tech.turtleracing.data.remote.model.Token as TokenRemote
import br.com.messore.tech.turtleracing.domain.model.Token as TokenDomain

internal fun TokenRemote.toDomain() = TokenDomain(
    token = token
)
