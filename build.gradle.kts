plugins {
    // "org.jetbrains.kotlin.jvm"
    kotlin("jvm") version "1.3.31" apply false

    id("org.jetbrains.intellij") version "0.4.10" apply false
}

subprojects {
    if (name == "intellij-codeclimate") {
        apply(plugin = "org.jetbrains.intellij")
        apply(plugin = "org.jetbrains.kotlin.jvm")
    }
}