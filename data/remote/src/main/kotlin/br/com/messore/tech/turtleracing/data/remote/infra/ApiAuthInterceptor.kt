package br.com.messore.tech.turtleracing.data.remote.infra

//class ApiAuthInterceptor(private val tokenRepository: TokenRepository) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val requestBuilder = chain.request()
//            .newBuilder()
//
//        val accessToken = tokenRepository.getToken()
//        accessToken?.let {
//            requestBuilder.useToken(it.token)
//        }
//
//        return chain.proceed(requestBuilder.build())
//    }
//}
