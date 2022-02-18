import extensions.internalModule

plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

androidLibConfig()

dependencies {
    internalModule(":domain")

    implementation(Libraries.timber)

    workerDependencies()
}
