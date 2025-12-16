plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "2.2.21"
}

group = "com.fatecitaquera"
version = "0.0.1-SNAPSHOT"
description = "Projeto para Carteirinha Digital"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("tools.jackson.module:jackson-module-kotlin")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

	// JUnit
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Security
	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	implementation("org.springframework.boot:spring-boot-starter-security")

	// Validation
	testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// JPA and Postgres
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	runtimeOnly("org.postgresql:postgresql")

	// E-Mail
	implementation("org.springframework.boot:spring-boot-starter-mail")
	testImplementation("org.springframework.boot:spring-boot-starter-mail-test")

	// JWT
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.7")
    implementation("io.jsonwebtoken:jjwt-api:0.12.7")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.7")

	// AWS/SUPABASE
	implementation("software.amazon.awssdk:s3:2.25.63")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
