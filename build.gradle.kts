plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "me.tatarka.nyandroid"
version = "1.5"

repositories {
    mavenCentral()
}

intellij {
//    version.set("2022.2.1.1")
//    type.set("AI")

//    version.set("2023.2.1")
    version.set("2021.3")
    type.set("IC")

    updateSinceUntilBuild.set(false)
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    signPlugin {
//        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
//        privateKey.set(System.getenv("PRIVATE_KEY"))
//        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(findProperty("org.gradle.project.intellijPublishToken")?.toString())
    }
}