plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id( "kotlin-parcelize")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion = Config.buildToolsVersion
    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)

        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation(core)

    testImplementation(Libs.test.junit)
    testImplementation(Libs.test.junitExt)
    testImplementation(Libs.test.robolectric)
    testImplementation(Libs.test.mockK)
    testImplementation(Libs.test.truth)
    testImplementation(Libs.test.truthExt)
    testImplementation(Libs.test.androidxTestRules)
    testImplementation(Libs.test.androidxTestRunner)
    testImplementation(Libs.test.androidxTestCore)
}