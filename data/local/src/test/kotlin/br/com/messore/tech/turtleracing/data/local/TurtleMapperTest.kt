package br.com.messore.tech.turtleracing.data.local

import br.com.messore.tech.turtleracing.data.local.mapper.toDomain
import br.com.messore.tech.turtleracing.data.local.mapper.toEntity
import br.com.messore.tech.turtleracing.data.local.model.TurtleEntity
import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import java.time.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TurtleMapperTest {

    //region domain
    @Test
    fun `toDomain should be converted successfully`() {
        val entity = createEntity()

        val domain = entity.toDomain()
        val expected = Turtle(
            id = "id",
            type = TurtleType.COMMON,
            timer = LocalTime.parse("00:20:00"),
            run = 2,
            energy = 100,
            age = 10,
            visibleId = "1234"
        )

        assertEquals(expected = expected, actual = domain)
    }

    @Test
    fun `toDomain should be throws an IllegalArgumentException When the type is wrong`() {
        val entity = createEntity().copy(type = "UNKNOWN")
        assertFailsWith<IllegalArgumentException> {
            entity.toDomain()
        }
    }
    //endregion

    //region entity
    @Test
    fun `toEntity should be converted successfully`() {
        val domain = createDomain()

        val entity = domain.toEntity()

        val expected = TurtleEntity(
            id = "id",
            type = TurtleType.COMMON.name,
            timer = LocalTime.parse("08:12:34"),
            run = 4,
            energy = 65,
            age = 25,
            visibleId = "1234"
        )
        assertEquals(expected = expected, actual = entity)
    }

    //endregion

    private fun createEntity(): TurtleEntity {
        return TurtleEntity(
            id = "id",
            type = TurtleType.COMMON.name,
            age = 10,
            energy = 100,
            run = 2,
            timer = LocalTime.of(0, 20, 0),
            visibleId = "1234"
        )
    }

    private fun createDomain(): Turtle {
        return Turtle(
            id = "id",
            type = TurtleType.COMMON,
            age = 25,
            energy = 65,
            run = 4,
            timer = LocalTime.of(8, 12, 34),
            visibleId = "1234"
        )
    }
}
