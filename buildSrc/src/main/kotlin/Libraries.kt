object Libraries {

    object AndroidX {
        object Ktx {
            const val core = "androidx.core:core-ktx:${Versions.AndroidX.Ktx.core}"
            const val activity = "androidx.activity:activity-ktx:${Versions.AndroidX.Ktx.activity}"
            const val work = "androidx.work:work-runtime-ktx:${Versions.AndroidX.Ktx.work}"
        }

        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
    }

    object Compose {
        const val coil = "io.coil-kt:coil-compose:${Versions.Compose.coil}"
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.ui}"
        const val toolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.toolingPreview}"
        const val material = "androidx.compose.material:material:${Versions.Compose.material}"
        const val activity = "androidx.activity:activity-compose:${Versions.Compose.activity}"

        const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.uiTest}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.uiTooling}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.Dagger.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.Dagger.compiler}"
    }

    object Hilt {
        const val work = "androidx.hilt:hilt-work:${Versions.Hilt.work}"
        const val workCompiler = "androidx.hilt:hilt-compiler:${Versions.Hilt.work}"
        const val android = "com.google.dagger:hilt-android:${Versions.Hilt.android}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt.compiler}"
    }

    object Kotlin {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    }

    object Lifecycle {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }


    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"
    }

    object SquareUp {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.Room.runtime}"
        const val ktx = "androidx.room:room-ktx:${Versions.Room.ktx}"
        const val compiler = "androidx.room:room-compiler:${Versions.Room.compiler}"
        const val testing = "androidx.room:room-testing:${Versions.Room.testing}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val mockk = "io.mockk:mockk:${Versions.Test.mockk}"
        const val runner = "androidx.test:runner:${Versions.Test.runner}"

        const val junitExt = "androidx.test.ext:junit:${Versions.Test.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.Test.kotlinTest}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.coroutines}"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}
