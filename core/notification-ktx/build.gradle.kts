import extensions.implementation
import extensions.internalModule

plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

androidLibConfig()

dependencies {
    internalModule(":domain")

    implementation(Libraries.AndroidX.Ktx.core)
}
