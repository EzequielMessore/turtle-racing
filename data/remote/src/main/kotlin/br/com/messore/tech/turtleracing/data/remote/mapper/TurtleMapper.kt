package br.com.messore.tech.turtleracing.data.remote.mapper

import br.com.messore.tech.turtleracing.domain.extensions.toDuration
import br.com.messore.tech.turtleracing.domain.extensions.toLocalTime
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import br.com.messore.tech.turtleracing.data.remote.model.Turtle as TurtleRemote
import br.com.messore.tech.turtleracing.domain.model.Turtle as TurtleDomain

internal fun List<TurtleRemote>.toDomain() = map { it.toDomain() }

// todo convert on domain the energy and age to Integer.
internal fun TurtleRemote.toDomain() = TurtleDomain(
    id = id,
    energy = energy.toLong(),
    type = type,
    age = age.toLong(),
    run = run,
    timer = timer.toLocalTime(),
    missingRun = missingRun.split("/").first().toInt(),
    visibleId = idF,
    image = getTurtleImage(),
    expirationTime = timer.toLocalTime().toDuration().toMillis() + System.currentTimeMillis()
)

private fun TurtleRemote.getTurtleImage() =
    "https://play.turtleracing.io/img/${getTurtleImageName()}${if (energy == 0) "T" else ""}.png"

private fun TurtleRemote.getTurtleImageName(): String? {
    return when {
        obs == null -> "skin_${skinTurtle}_$layout"
        obs.equals(skinTurtle, true) -> "skin_$skinTurtle"
        obs.endsWith("poke") -> skinTurtle
        skinTurtle.isEmpty() -> type.toSkinImage(layout)
        skinTurtle == "young_hulk" -> "skin_hulk_young"
        else -> null
    }
}

private fun TurtleType.toSkinImage(layout: Int): String? {
    return when (this) {
        TurtleType.COMMON -> "COMUM_$layout"
        TurtleType.RARE -> "RARA_$layout"
        TurtleType.LEGENDARY -> TurtleType.LEGENDARY.name
        else -> null
    }
}
