import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Config {
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val minSdkVersion = 23
    const val buildToolsVersion = "30.0.1"
    const val kotlinVersion = "1.4.21"
    const val appId = "com.marchuk.test.blog"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object GradlePlugins {
    private const val androidBuildToolsVersion = "4.1.1"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Config.kotlinVersion}"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Config.kotlinVersion}"

    val ui = UI
    val androidX = AndroidX
    val room = Room
    val test = Test
    val dagger = Dagger
    val retrofit = Retrofit
    val moshi = Moshi
    val timber = Timber
    val navigation = Navigation
    val rx = Rx

    object UI {
        private const val materialComponentsVersion = "1.1.0"
        private const val adapterDelegateVersion = "4.3.0"
        private const val expandableTextViewVersion = "1.0.5"

        const val materialComponents = "com.google.android.material:material:$materialComponentsVersion"
        const val adapterDelegateKotlinDsl = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:$adapterDelegateVersion"
        const val adapterDelegateKotlinDslViewBinding =
            "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:$adapterDelegateVersion"

        const val expandableTextView = "at.blogc:expandabletextview:$expandableTextViewVersion"
    }

    object Dagger {
        private const val daggerVersion = "2.30.1"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerAndroid = "com.google.dagger:dagger-android:${daggerVersion}"
        const val annotationProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object Rx {
        private const val rxJavaVersion = "2.2.7"
        private const val rxAndroidVersion = "2.1.1"

        const val java = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
        const val android = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
    }

    object Timber {
        private const val timberVersion = "4.7.1"

        const val timber = "com.jakewharton.timber:timber:$timberVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.8.1"

        const val refrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    }

    object Moshi {
        private const val moshiVersion = "1.11.0"

        const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    }

    object AndroidX {
        private const val constraintLayoutVersion = "2.0.4"
        private const val appCompatVersion = "1.2.0"
        private const val coreKtxVersion = "1.3.2"
        private const val recyclerViewVersion = "1.1.0"
        private const val viewModelVersion = "2.2.0"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVersion"
        const val viewModelExtensions = "androidx.lifecycle:lifecycle-extensions:$viewModelVersion"
    }

    object Navigation {
        private const val navigationVersion = "2.3.1"

        const val navigationKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    }

    object Room {
        private const val roomVersion = "2.2.5"

        const val roomCompilerKapt = "androidx.room:room-compiler:$roomVersion"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomRxJava = "androidx.room:room-rxjava2:$roomVersion"
    }

    object Test {
        private const val junitVersion = "4.13"
        private const val junitExtVersion = "1.1.1"
        private const val robolectricVersion = "4.3.1"
        private const val mockKVersion = "1.9.3"
        private const val truthExtVersion = "1.3.0-alpha03"
        private const val truthVersion = "1.0"
        private const val androidxTestRunnerVersion = "1.3.0-alpha03"
        private const val coreTestingVersion = "2.1.0"

        const val androidxTestRunner = "androidx.test:runner:$androidxTestRunnerVersion"
        const val androidxTestRules = "androidx.test:rules:$androidxTestRunnerVersion"
        const val androidxTestCore = "androidx.arch.core:core-testing:$coreTestingVersion"

        const val truth = "com.google.truth:truth:$truthVersion"
        const val truthExt = "androidx.test.ext:truth:$truthExtVersion"
        const val mockK = "io.mockk:mockk:$mockKVersion"
        const val junit = "junit:junit:$junitVersion"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"
        const val robolectric = "org.robolectric:robolectric:$robolectricVersion"
    }

}

inline val DependencyHandler.core get() = project(":app-core")
inline val DependencyHandler.utils get() = project(":app-utils")
inline val DependencyHandler.feature_posts get() = project(":feature-posts")
inline val DependencyHandler.feature_post_details get() = project(":feature-post-details")


