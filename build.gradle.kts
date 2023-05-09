plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.5.1"
    id("xyz.jpenilla.run-paper") version "2.0.1" // Adds runServer and runMojangMappedServer tasks for testing
    kotlin("jvm") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "fr.syrql"
version = "1.0.0-SNAPSHOT"
description = "HypingBees plugin"

allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        }
        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            url = uri("https://mvnrepository.com/artifact/org.redisson/redisson")
        }

        maven {
            url = uri("https://repo.codemc.org/repository/maven-public/")
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    paperweight.paperDevBundle("1.18.2-R0.1-SNAPSHOT")

    implementation("net.kyori:adventure-api:4.13.0")

    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("com.github.LoneDev6:API-ItemsAdder:3.2.5")

    compileOnly("world.bentobox:bentobox:1.20.1-SNAPSHOT")

    compileOnly("com.github.decentsoftware-eu:decentholograms:2.8.1")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    assemble {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    shadowJar {
        dependencies {
            include(dependency("world.bentobox:bentobox"))
        }
    }

    reobfJar {
        outputJar.set(file("/output/HypingBees-${project.version}.jar"))
    }
}
