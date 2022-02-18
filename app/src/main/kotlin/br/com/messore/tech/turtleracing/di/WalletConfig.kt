package br.com.messore.tech.turtleracing.di

import br.com.messore.tech.turtleracing.BuildConfig
import br.com.messore.tech.turtleracing.domain.model.ApiConfig
import dagger.Reusable
import javax.inject.Inject

@Reusable
class WalletConfig @Inject constructor() : ApiConfig {
    override val wallet = BuildConfig.wallet
    override val sign = BuildConfig.sign
    override val hash = BuildConfig.hash
}
