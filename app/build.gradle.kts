plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // id("com.android.application")
    // kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.beauty_salon_belyaev"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.beauty_salon_belyaev"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        compose = true
    }
}

kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.ui)
    implementation(libs.androidx.appcompat.v151)
    implementation(libs.androidx.room.runtime)
    implementation (libs.androidx.room.runtime.v242)
    implementation(libs.androidx.recyclerview)
    kapt (libs.androidx.room.compiler.v242)
    implementation (libs.kotlinx.coroutines.core.v152)
    implementation (libs.kotlinx.coroutines.android.v152)
    kapt(libs.androidx.room.compiler)
    implementation(libs.jetbrains.kotlinx.coroutines.core)
    implementation(libs.jetbrains.kotlinx.coroutines.android)
    implementation (libs.androidx.core.core.ktx.v1120)
    implementation (libs.kotlinx.coroutines.android.v173)
    implementation (libs.androidx.room.runtime.v251)
    implementation (libs.google.gson)
    implementation(libs.androidx.room.runtime.v251)
    kapt(libs.androidx.room.room.compiler.v251.x2)
    implementation(libs.jetbrains.kotlin.stdlib)
    kapt (libs.androidx.room.compiler.v250)
    implementation (libs.jetbrains.kotlin.stdlib)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}