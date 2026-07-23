import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.benchmark)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(libs.versions.jvm.toolchain.get().toInt())

    jvm()

    macosArm64()
    linuxX64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { nodejs() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmWasi { nodejs() }
    js { nodejs() }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.benchmark.runtime)
            }
        }
    }
}

benchmark {
    targets {
        register("jvm")
        register("macosArm64")
        register("linuxX64")
        register("js")
        register("wasmJs")
        register("wasmWasi")
    }

    configurations {
        named("main") {
            advanced("jvmForks", 3)
        }
    }
}
