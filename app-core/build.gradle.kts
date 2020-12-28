plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
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
    buildTypes {
        forEach { buildType ->
            buildType?.run {
                buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            }
        }
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
    // Kotlin
    api(utils)
    api(Libs.kotlin)

    // AndroidX
    api(Libs.androidX.coreKtx)
    api(Libs.androidX.appCompat)

    // Dagger
    api(Libs.dagger.dagger)
    api(Libs.dagger.daggerAndroid)
    kapt(Libs.dagger.annotationProcessor)
    kapt(Libs.dagger.compiler)

    // Navigation
    api(Libs.navigation.navigationKtx)

    // Jetpack
    kapt(Libs.room.roomCompilerKapt)
    api(Libs.room.roomKtx)
    api(Libs.room.roomRuntime)
    api(Libs.room.roomRxJava)

    // UI libraries
    api(Libs.ui.materialComponents)
    api(Libs.androidX.constraintLayout)
    api(Libs.androidX.recyclerView)

    // ViewModel
    api(Libs.androidX.viewModelKtx)
    api(Libs.androidX.viewModelExtensions)

    // RxJava
    api(Libs.rx.java)
    api(Libs.rx.android)

    // Timber
    api(Libs.timber.timber)

    // Retrofit
    api(Libs.retrofit.refrofit)
    api(Libs.retrofit.rxJavaAdapter)
    api(Libs.retrofit.retrofitMoshiConverter)

    // Moshi
    kapt(Libs.moshi.moshiKapt)
    api(Libs.moshi.moshiKotlin)

    // Navigation
    api(Libs.ui.adapterDelegateKotlinDsl)
    api(Libs.ui.adapterDelegateKotlinDslViewBinding)
    api(Libs.ui.expandableTextView)

    // Test libraries
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