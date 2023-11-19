// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.agp)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.hilt.plugin)
    }
}

plugins{
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.safeargs) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}


