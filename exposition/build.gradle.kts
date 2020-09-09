dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("javax.xml.bind:jaxb-api")
    implementation("io.springfox:springfox-boot-starter")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.springframework:spring-test")
    testImplementation("junit:junit:4.13")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("io.rest-assured:rest-assured:3.0.7")
}
