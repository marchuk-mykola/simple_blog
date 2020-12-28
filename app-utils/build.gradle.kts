plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.kotlin)

    testImplementation(Libs.test.junit)
    testImplementation(Libs.test.junitExt)
    testImplementation(Libs.test.truth)
    testImplementation(Libs.test.truthExt)
}