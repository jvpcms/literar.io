plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.agoravai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.agoravai"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Retrofit
    implementation(libs.retrofit)

    // Converter Gson para Retrofit
    implementation(libs.converter.gson)

    // Gson
    implementation(libs.gson)  // Dependência do Gson

    implementation("com.squareup.okhttp3:okhttp:4.10.0") // Biblioteca principal do OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
}