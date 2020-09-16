dependencies {
    implementation(project(":domain"))
    implementation("com.github.cukedoctor:cukedoctor-main:3.2")

    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("io.rest-assured:json-path:3.0.7")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}