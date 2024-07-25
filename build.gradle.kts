plugins {
    kotlin("jvm") version "1.9.23"
}

group = "ru.DmN.pht"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api("ru.DmN.siberia:Project-Siberia:1.23.1")
    api("ru.DmN.pht:Project-Pihta:1.25.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
}