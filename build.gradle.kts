plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.diffplug.spotless") version "6.21.0"
	id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
}

subprojects {
	apply(plugin = "com.diffplug.spotless")

	spotless {
		kotlin {
			target("src/**/*.kt")
			ktlint("0.50.0").userData(mapOf(
				"indent_size" to "4",
				"continuation_indent_size" to "4"
			))
		}

		kotlinGradle {
			target("*.gradle.kts")
			ktlint("0.50.0")
		}
	}

	tasks.named("check") {
		dependsOn("spotlessCheck")
	}
}

group = "xcode"
version = "1.0.0-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("com.github.ulisesbocchio:jasypt-spring-boot:3.0.5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	implementation("org.postgresql:postgresql:42.7.4")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
