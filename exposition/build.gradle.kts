plugins {
    id("com.google.cloud.tools.jib") version "3.1.2"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":repository"))

    implementation("com.h2database:h2")
    implementation("io.springfox:springfox-boot-starter")
    implementation("javax.xml.bind:jaxb-api")
    implementation ("org.hibernate:hibernate-core")
    implementation("org.liquibase:liquibase-core")
    implementation ("org.mapstruct:mapstruct:1.3.1.Final")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-jdbc")
    implementation ("org.springframework.boot:spring-boot-starter-log4j2:2.4.1")
    implementation ("org.springframework.boot:spring-boot-devtools:2.4.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation ("org.slf4j:jul-to-slf4j:1.7.30")
    implementation ("org.apache.logging.log4j:log4j-api:2.14.0")
    implementation ("org.apache.logging.log4j:log4j-core:2.14.0")
    implementation ("org.apache.logging.log4j:log4j-jul:2.14.0")
    implementation ("org.apache.logging.log4j:log4j-slf4j-impl:2.14.0")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("io.rest-assured:rest-assured")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.springframework:spring-test")
}

tasks.register<Copy>("buildWithDependencies") {
    description = "Build the module ans copies the dependencies to the $buildDir/libs directory."
    dependsOn("buildNeeded")
    into("$buildDir/libs")
    from(configurations["compileClasspath"])
}