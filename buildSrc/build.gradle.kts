import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
    maven(url = "https://jitpack.io")
    maven {
        url = uri("https://jitpack.io")
    }
    google()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}
