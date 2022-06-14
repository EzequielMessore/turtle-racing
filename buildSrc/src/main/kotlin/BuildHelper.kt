import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.DefaultConfig
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import configs.AppConfig
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.android(configure: Action<BaseAppModuleExtension>) =
    (this as ExtensionAware).extensions.configure("android", configure)

internal fun Project.androidLib(configure: LibraryExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure("android", configure)

internal fun BaseAppModuleExtension.kotlinOptions(configure: Action<KotlinJvmOptions>) =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)

internal fun LibraryExtension.kotlinOptions(configure: Action<KotlinJvmOptions>) =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)

internal fun Project.java(configure: Action<org.gradle.api.plugins.JavaPluginExtension>) =
    (this as ExtensionAware).extensions.configure("java", configure)

fun Project.androidConfig(
    defaultConfig: ApplicationDefaultConfig.() -> Unit = {},
    debugType: ApplicationBuildType.() -> Unit = {},
    releaseType: ApplicationBuildType.() -> Unit = {},
    anyConfig: BaseAppModuleExtension.() -> Unit = {},
) = android {
    compileSdk = AppConfig.Sdk.compile

    defaultConfig {
        defaultConfig(this)
        minSdk = AppConfig.Sdk.min
        targetSdk = AppConfig.Sdk.target
        versionCode = AppConfig.Version.code
        versionName = AppConfig.Version.name

        testInstrumentationRunner = AppConfig.instrumentationTestRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    anyConfig(this)

    buildTypes {
        debug(debugType)
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            releaseType(this)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

fun Project.androidLibConfig(
    androidConfig: LibraryExtension.() -> Unit = {},
    defaultConfig: DefaultConfig.() -> Unit = {},
) = androidLib {
    compileSdk = AppConfig.Sdk.compile

    defaultConfig {
        minSdk = AppConfig.Sdk.min
        targetSdk = AppConfig.Sdk.target

        testInstrumentationRunner = AppConfig.instrumentationTestRunner
        consumerProguardFiles("consumer-rules.pro")
        defaultConfig()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    androidConfig(this)
}
