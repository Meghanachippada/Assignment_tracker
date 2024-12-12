plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
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
    // Lifecycle and Firebase dependencies
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")

    // Firebase dependencies
    implementation(platform(libs.firebase.bom)) // Firebase BoM
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database.ktx)

    // Glide dependencies
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1") // Required for Glide annotations

    // AndroidX dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation("com.airbnb.android:lottie:6.0.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")


    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:core-ktx:1.6.0")
    androidTestImplementation("androidx.test:runner:1.5.0")
    androidTestImplementation("androidx.test:rules:1.5.0")


}