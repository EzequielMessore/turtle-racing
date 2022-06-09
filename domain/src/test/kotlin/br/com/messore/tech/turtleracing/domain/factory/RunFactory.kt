package br.com.messore.tech.turtleracing.domain.factory

import br.com.messore.tech.turtleracing.domain.model.Run

object RunFactory {
    fun getRun(): Run {
        return Run(
            position = 1,
            profit = "1.50"
        )
    }
}
