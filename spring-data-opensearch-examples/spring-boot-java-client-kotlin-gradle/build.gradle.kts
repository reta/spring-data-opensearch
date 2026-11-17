/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(springLibs.plugins.spring.boot)
  alias(pluginLibs.plugins.spotless)
  alias(pluginLibs.plugins.editorconfig)
  alias(pluginLibs.plugins.kotlin.jvm)
  alias(pluginLibs.plugins.kotlin.spring)
  id("java-conventions")
  id("org.graalvm.buildtools.native") version "1.1.18"
}

buildscript {
  dependencies {
    classpath(pluginLibs.editorconfig)
    classpath(pluginLibs.spotless)
  }
}

dependencies {
  implementation(project(":spring-data-opensearch")) {
    exclude("org.opensearch.client", "opensearch-rest-high-level-client")
  }
  implementation(project(":spring-data-opensearch-starter")) {
    exclude("org.opensearch.client", "opensearch-rest-high-level-client")
  }
  implementation(kotlin("reflect"))
  implementation(springLibs.boot.web)
  implementation(jacksonLibs.core)
  implementation(jacksonLibs.databind)
  implementation(opensearchLibs.client)
  implementation(opensearchLibs.java.client) {
    exclude("com.fasterxml.jackson.core", "*")
    exclude("com.fasterxml.jackson.databind", "*")
  }
  testImplementation(springLibs.test)
  testImplementation(springLibs.boot.test)
  testImplementation(springLibs.boot.test.autoconfigure)
  testImplementation(springLibs.boot.testcontainers)
  testImplementation(opensearchLibs.testcontainers)
  testImplementation(project(":spring-data-opensearch-testcontainers")) {
    exclude("org.opensearch.client", "opensearch-rest-high-level-client")
  }
  testImplementation(project(":spring-data-opensearch-test-autoconfigure")) {
    exclude("org.opensearch.client", "opensearch-rest-high-level-client")
  }

  constraints {
    implementation("ch.qos.logback:logback-classic") {
      version {
        require("1.4.12")
      }
      because("Fixes CVE-2023-6378")
    }
  }
}

description = "Spring Data OpenSearch Java Client Spring Boot Kotlin Example Project"

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_21
    javaParameters = true
    freeCompilerArgs.add("-Xjsr305=strict")
  }
}

spotless {
  kotlin {
    target("src/main/kotlin/**/*.kt", "src/test/kotlin/org/opensearch/**/*.kt")

    trimTrailingWhitespace()
    leadingTabsToSpaces()
    endWithNewline()
  }
}
