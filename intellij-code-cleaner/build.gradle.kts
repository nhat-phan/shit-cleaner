import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactGroup: String by project
val artifactVersion: String by project
val intellijVersion: String by project
val jvmTarget: String by project
val foundationVersion: String by project

group = artifactGroup
version = artifactVersion

repositories {
    jcenter()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.github.nhat-phan.foundation:foundation-jvm:$foundationVersion")
    implementation(project(":intellij-code-cleaner-components"))
    implementation(project(":contracts"))
    implementation(project(":core"))
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = intellijVersion
    updateSinceUntilBuild = false
}

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = jvmTarget
}

compileTestKotlin.kotlinOptions {
    jvmTarget = jvmTarget
}

tasks {
    named<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
        changeNotes(htmlFixer("intellij-code-cleaner/doc/release-notes.$artifactVersion.html"))
        pluginDescription(htmlFixer("intellij-code-cleaner/doc/description.html"))
    }
}

fun htmlFixer(filename: String): String {
    if (!File(filename).exists()) {
        throw Exception("File $filename not found.")
    }
    return File(filename).readText().replace("<html lang=\"en\">", "").replace("</html>", "")
}