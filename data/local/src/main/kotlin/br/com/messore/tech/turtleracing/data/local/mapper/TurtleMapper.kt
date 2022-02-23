package br.com.messore.tech.turtleracing.data.local.mapper

import br.com.messore.tech.turtleracing.data.local.model.TurtleEntity
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import br.com.messore.tech.turtleracing.domain.model.Turtle as TurtleDomain

internal fun TurtleEntity.toDomain(): TurtleDomain {
    return TurtleDomain(
        id = id,
        energy = energy,
        type = TurtleType.valueOf(type),
        age = age,
        run = run,
        timer = timer
    )
}

internal fun TurtleDomain.toEntity(): TurtleEntity {
    return TurtleEntity(
        id = id,
        energy = energy,
        type = type.name,
        age = age,
        run = run,
        timer = timer
    )
}