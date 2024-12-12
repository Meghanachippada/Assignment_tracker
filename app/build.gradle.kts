plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.assignment_tracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.assignment_tracker"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        compose = false // Jetpack Compose is not used
    }
}

dependencies {
    // Lifecycle dependencies
    implementation(libs.androidx.lifecycle.runtime.ktx.v260)

    // Firebase dependencies
    implementation(platform(libs.firebase.bom)) // Firebase BoM
    implementation(libs.firebase.auth)
    implementation(libs.firebase.auth) // Add the Firebase Auth library
    implementation(libs.firebase.database.ktx)

    // Glide dependency
    implementation(libs.glide)
    kapt(libs.compiler)

    // AndroidX dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit) // JUnit dependency
    androidTestImplementation(libs.androidx.junit.v115) // AndroidX JUnit extension
    androidTestImplementation(libs.core.ktx) // Core testing utilities
    androidTestImplementation(libs.androidx.runner) // Android test runner
    androidTestImplementation(libs.androidx.rules) // Android test rules

    // Add any other dependencies you might need
}