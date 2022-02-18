package configs

object AppConfig {
    const val appId = "br.com.messore.tech.turtleracing"

    object Sdk {
        const val compile = 31
        const val min = 27
        const val target = 30
    }

    object Version {
        const val code = 1
        const val name = "1.0.0"
    }

    const val instrumentationTestRunner = "androidx.test.runner.AndroidJUnitRunner"
}