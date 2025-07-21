plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.sachin.matchmaking"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sachin.matchmaking"
        minSdk = 27
        targetSdk=  35
        versionCode= 1
        versionName ="1.0"

        testInstrumentationRunner  ="androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }

    packaging {
        resources {
            excludes += mutableSetOf("META-INF/NOTICE.md", "META-INF/LICENSE.md", "META-INF/INDEX.LIST", "META-INF/DEPENDENCIES", "META-INF/io.netty.versions.properties")

        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.paging.common.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Retrofit for API calls
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)


    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Paging
    implementation(libs.paging.runtime)
    // Room core library
    implementation(libs.androidx.room.runtime)

    // Kotlin Extensions and Coroutines support
    implementation(libs.androidx.room.ktx)

    // Paging 3 support with Room
    implementation(libs.androidx.room.paging)

    // Room annotation processor
    kapt(libs.androidx.room.compiler)
}