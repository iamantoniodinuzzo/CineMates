// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath(libs.agp)
//        classpath(libs.kotlin.gradlePlugin)
//        classpath(libs.navigation.safeargs)
//    }
//}

plugins {
    id(libs.plugins.hilt.plugin.get().pluginId) version libs.versions.hiltPlugin apply false
    id(libs.plugins.kotlin.android.get().pluginId) version libs.versions.kotlin apply false
    id(libs.plugins.safeargs.get().pluginId) version libs.versions.navigation apply false
    id(libs.plugins.kotlin.kapt.get().pluginId) version libs.versions.kotlin apply false
//    id(libs.plugins.android.application.get().pluginId) version libs.versions.agp apply false
//    id(libs.plugins.android.library.get().pluginId) version libs.versions.agp apply false

}



