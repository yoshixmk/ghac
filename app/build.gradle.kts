@file:Suppress("UnstableApiUsage")

import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.ghac"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.ghac"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // propertiesファイルから読み取って、BuildConfigへGitHub PATを定義
        val properties = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }
        val githubPat = properties.getProperty("github.pat")
        buildConfigField("String", "GITHUB_PAT", "\"${githubPat}\"")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
}

val composeUiVersion: String by project
val kotestVersion: String by project
val hiltVersion: String by project
val junitVersion: String by project
val navigationComposeVersion: String by project

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.compose.material3:material3:1.1.1")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.navigation:navigation-compose:$navigationComposeVersion")
    implementation("androidx.paging:paging-runtime:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha20")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:${rootProject.extra["kotestVersion"]}")
    testImplementation("io.mockk:mockk:1.13.5")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navigationComposeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.11")
}

kapt {
    correctErrorTypes = true
}
