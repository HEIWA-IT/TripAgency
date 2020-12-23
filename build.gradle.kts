import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    `java-library`
    `maven-publish`
    checkstyle
    jacoco
}

allprojects {
    val groupId: String by project
    val projectVersion: String by project

    group = groupId
    version = projectVersion

    apply { plugin("java") }
    apply { plugin("maven-publish") }
    apply { plugin("jacoco") }

    repositories {
        jcenter()
        mavenCentral()

        val mavenUser: String by project
        val mavenPassword: String by project
        val snapshotsRepoUrl: String by project
        val releasesRepoUrl: String by project

        repositories {
            maven {
                url = if (version.toString().endsWith("SNAPSHOT")) uri(snapshotsRepoUrl) else uri(releasesRepoUrl)
                credentials {
                    username = mavenUser
                    password = mavenPassword
                }
            }
        }

        maven {
            url = uri("https://repo.maven.apache.org/maven2")
        }
    }

    publishing {
        val mavenUser: String by project
        val mavenPassword: String by project
        val snapshotsRepoUrl: String by project
        val releasesRepoUrl: String by project

        repositories {
            maven {
                url = if (version.toString().endsWith("SNAPSHOT")) uri(snapshotsRepoUrl) else uri(releasesRepoUrl)
                credentials {
                    username = mavenUser
                    password = mavenPassword
                }
            }
        }

        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                artifactId = tasks.jar.get().archiveBaseName.get()
            }
        }
    }

    jacoco {
        toolVersion = "0.8.6"
        reportsDir = file("$buildDir/jacoco")
    }

    tasks.jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
            html.isEnabled = true
        }
    }

    tasks.test {
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test) // tests are required to run before generating the report
    }
}

tasks.compileJava {
    // use the project's version or define one directly
    options.javaModuleVersion.set(provider { project.version as String })
}

subprojects {
    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    val javaRestProjectVersion: String by project

    dependencies {
        dependencies {
            implementation(platform("com.paxleones:java-rest-project:$javaRestProjectVersion"))
        }

        tasks.withType<Test> {
            useJUnitPlatform()
            testLogging {
                showExceptions = true
                exceptionFormat = TestExceptionFormat.FULL
                showStackTraces = true
                showCauses = true
                showStandardStreams = true
                events = setOf(
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.FAILED,
                        TestLogEvent.STANDARD_OUT,
                        TestLogEvent.STANDARD_ERROR
                )
            }
        }
    }
}