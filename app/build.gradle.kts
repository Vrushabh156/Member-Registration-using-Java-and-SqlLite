plugins {
    alias(libs.plugins.android.application)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.vrushabh.assignment_member_registration"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vrushabh.assignment_member_registration"
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

    buildFeatures {
        viewBinding = true
    }

    dependencies {

        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)

        // SQLite
        implementation("androidx.sqlite:sqlite:2.4.0")

        // Dependency Injection (Hilt)
        implementation("com.google.dagger:hilt-android:2.50")
        annotationProcessor("com.google.dagger:hilt-android-compiler:2.50")

        // ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    }
}