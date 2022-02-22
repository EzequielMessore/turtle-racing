import extensions.internalModule

plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.hilt)
}

androidLibConfig()

dependencies {
    internalModule(":data:data")

    hiltDependencies()
    roomDependencies()
}