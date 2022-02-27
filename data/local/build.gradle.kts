import extensions.internalModule

plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.hilt)
}

androidLibConfig {
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    internalModule(":data:data")

    androidTestDependencies()

    hiltDependencies()
    roomDependencies()
}
