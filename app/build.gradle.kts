plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

repositories {
    jcenter()
    google()
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)

        applicationId = Config.appId
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(core)
    implementation(feature_posts)
    implementation(feature_post_details)

    implementation(Libs.dagger.dagger)
    kapt(Libs.dagger.annotationProcessor)
    kapt(Libs.dagger.compiler)

}