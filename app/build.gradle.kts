plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.pieski"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.pieski"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // Wersja kompilatora Compose kompatybilna z Kotlin 1.8.21
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    packagingOptions {
        resources.excludes += "META-INF/*"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21")
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}
