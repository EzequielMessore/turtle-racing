import extensions.internalModule

plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.hilt)
}

androidLibConfig(
    defaultConfig = {
        javaCompileOptions {
            annotationProcessorOptions {
                println("$projectDir/src/androidTest/kotlin/schemas/")
                arguments += mapOf("room.schemaLocation" to "$projectDir/src/androidTest/schemas/")
            }
        }
    },
    androidConfig = {
        packagingOptions {
            resources.excludes.add("META-INF/*")
        }

        sourceSets {
            getByName("androidTest").assets.srcDir("$projectDir/src/androidTest/schemas/")
        }
    }
)

dependencies {
    internalModule(":data:data")

    testImplementation(Libraries.Test.kotlinTest)
    androidTestDependencies()

    hiltDependencies()
    roomDependencies()
}
