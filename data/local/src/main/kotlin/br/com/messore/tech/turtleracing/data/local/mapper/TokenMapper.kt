package br.com.messore.tech.turtleracing.data.local.mapper

import br.com.messore.tech.turtleracing.data.local.model.TokenEntity
import br.com.messore.tech.turtleracing.domain.model.Token as TokenDomain

fun TokenDomain.toEntity() =
    TokenEntity(id = 1, token = token)

fun TokenEntity.toDomain() =
    TokenDomain(token = token)
