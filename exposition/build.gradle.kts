plugins {
    id("com.google.cloud.tools.jib") version "2.6.0"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":repository"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("javax.xml.bind:jaxb-api")
    implementation("io.springfox:springfox-boot-starter")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.assertj:assertj-core")
    testImplementation("com.h2database:h2")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.mockito:mockito-junit-jupiter")
}