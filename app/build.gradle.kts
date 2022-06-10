import configs.AppConfig
import extensions.androidTestImplementation
import extensions.implementation
import extensions.internalModule
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
            if (file.exists()) load(file.reader())
        }

        buildConfigField("String", "wallet", properties.getProperty("wallet", "\"\""))
        buildConfigField("String", "sign", properties.getProperty("sign", "\"\""))
        buildConfigField("String", "hash", properties.getProperty("hash", "\"\""))
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
    internalModule(":core:notification-ktx")
    internalModule(":data:data")
    internalModule(":data:local")
    internalModule(":data:remote")
    internalModule(":domain")
    internalModule(":workers")

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
