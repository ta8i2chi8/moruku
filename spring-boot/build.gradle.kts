plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
  id("org.flywaydb.flyway") version "8.2.0"
}

group = "com.morimoto.taichi"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
  implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("com.google.firebase:firebase-admin:9.2.0")

	compileOnly("org.projectlombok:lombok:1.18.28")
  annotationProcessor("org.projectlombok:lombok:1.18.28")
  runtimeOnly("mysql:mysql-connector-java:8.0.33")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
  val enviroment = "dev"

  when (enviroment) {
    "dev" -> {
      url      = "jdbc:mysql://localhost:3306/moruku"
      user     = "dev_user"
      password = "dev_user_pass"
		}
		// TODO: update user and password for production
    "prd" -> {
      url      = "jdbc:mysql://localhost:3306/moruku"
      user     = "dev_user"
      password = "dev_user_pass"
		}
    else -> {
			// same dev
      url      = "jdbc:mysql://localhost:3306/moruku"
      user     = "dev_user"
      password = "dev_user_pass"
		}
  }
}