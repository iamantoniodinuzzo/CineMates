plugins {
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.common"
}

dependencies {
    implementation(project(":core:model:base"))
//    implementation(libs.bundles.androidX)
}
