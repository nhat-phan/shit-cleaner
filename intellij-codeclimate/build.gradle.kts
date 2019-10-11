import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactGroup: String by project
val intellijVersion: String by project
val intellijPluginVersion: String by project
val jvmTarget: String by project

group = artifactGroup
version = intellijPluginVersion

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = intellijVersion
}

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = jvmTarget
}

compileTestKotlin.kotlinOptions {
    jvmTarget = jvmTarget
}

//patchPluginXml {
//    changeNotes """
//      Add change notes here.<br>
//      <em>most HTML tags may be used</em>"""
//}