@file:Suppress("UNUSED_VARIABLE")

buildscript {
    val composeUiVersion by extra("1.4.3")
    val kotestVersion by extra("5.6.2")
    val hiltVersion by extra("2.46.1")
    val navigationComposeVersion by extra("2.6.0")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}
