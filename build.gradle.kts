plugins {
    // "org.jetbrains.kotlin.jvm"
    kotlin("jvm") version "1.3.31" apply false

    // "org.jetbrains.kotlin.kapt"
    kotlin("kapt") version "1.3.31" apply false

    // "kotlinx-serialization"
    id("kotlinx-serialization") version "1.3.31" apply false

    id("org.jetbrains.intellij") version "0.4.10" apply false
}

subprojects {
    if (name == "contracts") {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.kotlin.kapt")
    }

    if (name == "core") {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.kotlin.kapt")
        apply(plugin = "kotlinx-serialization")
    }

    if (name == "intellij-code-cleaner") {
        apply(plugin = "org.jetbrains.intellij")
        apply(plugin = "org.jetbrains.kotlin.jvm")
    }
}