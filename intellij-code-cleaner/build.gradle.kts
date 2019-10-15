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
    implementation(project(":contracts"))
    implementation(project(":core"))
    compile("org.apache.commons:commons-exec:1.3")
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