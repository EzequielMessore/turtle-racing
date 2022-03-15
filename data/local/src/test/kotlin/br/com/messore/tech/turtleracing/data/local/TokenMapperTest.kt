package br.com.messore.tech.turtleracing.data.local

import br.com.messore.tech.turtleracing.data.local.mapper.toDomain
import br.com.messore.tech.turtleracing.data.local.mapper.toEntity
import br.com.messore.tech.turtleracing.data.local.model.TokenEntity
import br.com.messore.tech.turtleracing.domain.model.Token
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenMapperTest {

    @Test
    fun `toDomain should be converted successfully`() {
        val entity = TokenEntity(id = 1, token = "entity-token")

        val domain = entity.toDomain()

        val expected = Token(token = "entity-token")
        assertEquals(expected, domain)
    }

    @Test
    fun `toEntity should be converted successfully`() {
        val domain = Token(token = "domain-token")

        val entity = domain.toEntity()

        val expected = TokenEntity(id = 1, token = "domain-token")
        assertEquals(expected, entity)
    }
}
