package br.com.messore.tech.turtleracing.domain.usecase.token

import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): String {
        return tokenRepository.getToken()?.token ?: ""
    }
}
