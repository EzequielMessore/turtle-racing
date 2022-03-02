import configs.AppConfig
import extensions.androidTestImplementation
import extensions.implementation
import extensions.internalApi
import extensions.testImplementation
import java.util.Properties

plugins {
    id(Plugins.Android.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.hilt)
}

androidConfig(
    defaultConfig = {
        applicationId = AppConfig.appId

        val properties = Properties().apply {
            val file = rootProject.file("wallet.properties")
            load(file.reader())
        }

        buildConfigField("String", "wallet", properties.getProperty("wallet"))
        buildConfigField("String", "sign", properties.getProperty("sign"))
        buildConfigField("String", "hash", properties.getProperty("hash"))
    },
    anyConfig = {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = Versions.Compose.compose
        }
    }
)

dependencies {
    internalApi(":data:data")
    internalApi(":data:local")
    internalApi(":data:remote")
    internalApi(":domain")
    internalApi(":workers")

    hiltDependencies()
    composeDependencies()
    androidXDependencies()
    lifecycleDependencies()
    coroutinesCoreDependencies()
    coroutinesAndroidDependencies()

    implementation(Libraries.AndroidX.Ktx.activity)
    implementation(Libraries.timber)

    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.junitExt)
    androidTestImplementation(Libraries.Test.espresso)

}