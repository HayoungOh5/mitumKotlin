plugins {
    kotlin("jvm") version "2.0.0" // or kotlin("multiplatform") or any other kotlin plugin
    kotlin("plugin.serialization") version "2.0.0"
    application
}

repositories {
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib-jdk8")) // Kotlin 표준 라이브러리
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("org.bitcoinj:bitcoinj-core:0.16.3") // Bitcoinj 라이브러리 추가
}

application {
    mainClass.set("MainKt")
}