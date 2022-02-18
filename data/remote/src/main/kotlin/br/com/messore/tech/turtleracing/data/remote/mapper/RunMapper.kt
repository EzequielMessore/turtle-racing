package br.com.messore.tech.turtleracing.data.remote.mapper

import br.com.messore.tech.turtleracing.domain.model.Run
import br.com.messore.tech.turtleracing.data.remote.model.Run as RunRemote

fun RunRemote.toDomain() = Run(
    profit = profit,
    position = position,
)
