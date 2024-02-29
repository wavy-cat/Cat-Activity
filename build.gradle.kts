plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.16.0"
}

group = "me.rerere"
version = "0.12.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.JnCrMx:discord-game-sdk4j:java-impl-SNAPSHOT")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("Git4Idea"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

//    signPlugin {
//        certificateChain.set(file("token/chain.crt").readText())
//        privateKey.set(file("token/private.pem").readText())
//        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
//    }
//
//    publishPlugin {
//        token.set(System.getenv("PUBLISH_TOKEN"))
//    }

    runIde {
//        ideDir.set(file(
//            "/Users/re/Library/Application Support/JetBrains/Toolbox/apps/Gateway/ch-0/231.9011.34/JetBrains Gateway.app/Contents"
//        ))
//        ideDir.set(
//            file(
//                "/Users/re/Library/Application Support/JetBrains/Toolbox/apps/WebStorm/ch-0/223.8836.50/WebStorm.app/Contents"
//            )
//        )
    }
}
