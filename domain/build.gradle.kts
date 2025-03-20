plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)

    //for Dagger 2
    id("kotlin-kapt")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    //Dagger 2
    implementation(libs.dagger)
    kapt(libs.google.dagger.compiler)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
}
