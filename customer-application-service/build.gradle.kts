import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	jacoco
}
group = "com.customer-application-service"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}
tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report

	classDirectories.setFrom(
		files(classDirectories.files.map {
			fileTree(it) {
				exclude("com/customerapplicationservice/dto**",
					"com/customerapplicationservice/repository/ApplicationUpdateRepo.class",
					"com/customerapplicationservice/modal**/CustomerLoanApplication.class",
					"com/customerapplicationservice/CustomerApplicationServiceApplicationKt.class",
				)
			}
		})
	)
}
jacoco {
	toolVersion = "0.8.7"
	reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}
tasks.jacocoTestReport {
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.projectlombok:lombok:1.18.16")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("junit:junit:4.4")
	testImplementation("io.mockk:mockk:1.10.6")
	testImplementation("io.projectreactor:reactor-test:3.4.3")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}