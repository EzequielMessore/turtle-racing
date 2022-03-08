plugins {
    id(Plugins.ktlint) version Versions.ktlint
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.gradle)
        classpath(Dependencies.kotlin)
        classpath(Dependencies.daggerHilt)
    }
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-Xopt-in=androidx.compose.ui.unit.ExperimentalUnitApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            )
            jvmTarget = "11"
        }
    }
    apply(plugin = Plugins.ktlint)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
