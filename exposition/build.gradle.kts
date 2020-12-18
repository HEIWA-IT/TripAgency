plugins {
    id("com.google.cloud.tools.jib") version "2.7.0"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":repository"))

    implementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("javax.xml.bind:jaxb-api")
    implementation("io.springfox:springfox-boot-starter")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.assertj:assertj-core")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

/*task copyDependencies (type: Copy) {
    from configurations.compile
            into 'dependencies'
}*/