import extensions.androidTestImplementation
import extensions.debugImplementation
import extensions.implementation
import extensions.testImplementation
import extensions.kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.androidXDependencies() = dependencies {
    implementation(Libraries.AndroidX.Ktx.core)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.Google.material)
    implementation(Libraries.AndroidX.Ktx.activity)
}

fun Project.composeDependencies() = dependencies {
    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.coil)
    implementation(Libraries.Compose.material)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.toolingPreview)

    androidTestImplementation(Libraries.Compose.uiTest)
    debugImplementation(Libraries.Compose.uiTooling)
}

fun Project.coroutinesAndroidDependencies() = dependencies {
    implementation(Libraries.Kotlin.coroutinesAndroid)
}

fun Project.coroutinesCoreDependencies() = dependencies {
    implementation(Libraries.Kotlin.coroutines)
}

fun Project.daggerDependencies() = dependencies {
    kapt(Libraries.Dagger.compiler)
    implementation(Libraries.Dagger.dagger)
}

fun Project.hiltDependencies() = dependencies {
    kapt(Libraries.Hilt.compiler)
    implementation(Libraries.Hilt.android)
}

fun Project.androidTestDependencies() = dependencies {
    androidTestImplementation(Libraries.Test.runner)
    androidTestImplementation(Libraries.Test.jupiter)
    androidTestImplementation(Libraries.Test.junitExt)
}

fun Project.jupiterDependencies() = dependencies {
    testImplementation(Libraries.Test.jupiter)
    testImplementation(Libraries.Test.junitExt)
}

fun Project.lifecycleDependencies() = dependencies {
    implementation(Libraries.Lifecycle.runtime)
}

fun Project.retrofitDependencies() = dependencies {
    implementation(Libraries.SquareUp.gson)
    implementation(Libraries.SquareUp.retrofit)
    implementation(Libraries.SquareUp.loggingInterceptor)
}

fun Project.roomDependencies() = dependencies {
    kapt(Libraries.Room.compiler)
    implementation(Libraries.Room.ktx)
    implementation(Libraries.Room.runtime)
    implementation(Libraries.Room.testing)
}

fun Project.workerDependencies() = dependencies {
    implementation(Libraries.AndroidX.Ktx.work)
}
