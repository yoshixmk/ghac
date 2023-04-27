buildscript {
    val composeUiVersion by extra("1.4.2")
    val pagingVersion by extra("3.1.1")
    val kotestVersion by extra("5.6.1")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}
