//import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}
apply<MainGradlePlugin>()

//val key: String = gradleLocalProperties(rootDir).getProperty("TMDB_API_KEY")

val apikeyPropertiesFile: File = rootProject.file("local.properties")
val apikeyProperties = Properties().apply {
    load(apikeyPropertiesFile.inputStream())
}
android {
    namespace = "com.indisparte.network"

    defaultConfig {
        buildConfigField("String", "TMDB_API_KEY",  apikeyProperties["TMDB_API_KEY"].toString())
    }

}

dependencies {
    implementation(libs.bundles.androidX)
    testImplementation(libs.junitTest)

    // Retrofit
    implementation(libs.bundles.retrofit)
    testImplementation(libs.androidx.arch.core)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Timber
    implementation(libs.timber)
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
}
