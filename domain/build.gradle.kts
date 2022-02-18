plugins {
    id(Plugins.Kotlin.kotlin)
    id(Plugins.Java.library)
    id(Plugins.Kotlin.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    daggerDependencies()
}
