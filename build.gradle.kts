plugins {
    id(Plugins.detekt) version Versions.detekt
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
        classpath(Dependencies.detekt)
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
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}")
}

detekt {
    toolVersion = Versions.detekt
    source = files(getSrcDirectors())
    autoCorrect = true

    config = files("${projectDir}/detekt/detekt.yml")
    reportsDir = file("${projectDir}/build/reports/detekt/")
}

/**
 * function responsible for retrieving all `src` folders of the project
 */
fun getSrcDirectors() = projectDir.walk().filter { file ->
    file.isDirectory && file.absolutePath.endsWith("src")
}.toList()
