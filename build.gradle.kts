// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.Gradle.gradle)
        classpath(Deps.Gradle.kotlin)
        classpath(Deps.Gradle.hilt)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://www.jitpack.io") }
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)

}