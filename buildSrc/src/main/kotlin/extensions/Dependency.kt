package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.implementation(dependency: String) =
    add("implementation", dependency)

fun DependencyHandlerScope.testImplementation(dependency: String) =
    add("testImplementation", dependency)

fun DependencyHandlerScope.internalModule(dependency: String) =
    add("implementation", project(dependency))

fun DependencyHandlerScope.kapt(dependency: String) =
    add("kapt", dependency)

fun DependencyHandlerScope.internalApi(dependency: String) =
    add("api", project(dependency))

fun DependencyHandlerScope.api(dependency: String) =
    add("api", dependency)

fun DependencyHandlerScope.androidTestImplementation(dependency: String) =
    add("androidTestImplementation", dependency)

fun DependencyHandlerScope.debugImplementation(dependency: String) =
    add("debugImplementation", dependency)
