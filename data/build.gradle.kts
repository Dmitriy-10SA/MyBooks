plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    //for Dagger 2
    id("kotlin-kapt")

    //for Room
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.andef.mybooks.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    //domain
    implementation(project(":domain"))

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.androidx.room.compiler)

    //Retrofit
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Logging Interceptor
    implementation(libs.logging.interceptor)

    //Dagger 2
    implementation(libs.dagger)
    kapt(libs.google.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}