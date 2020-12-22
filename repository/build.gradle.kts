dependencies {
    implementation(project(":domain"))
    implementation ("org.liquibase:liquibase-core")
    implementation ("org.springframework.boot:spring-boot-starter-jdbc")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.hibernate:hibernate-core")
    testImplementation ("org.postgresql:postgresql")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.springframework.boot:spring-boot-test")
    testImplementation ("org.springframework:spring-test")
    testImplementation ("org.assertj:assertj-core")
    testImplementation ("com.h2database:h2")
}
