plugins {
    `java-library`
    `maven-publish`
    checkstyle
    jacoco
}

allprojects {
    group = "com.heiwait"
    version = "0.0.1-SNAPSHOT"

    apply { plugin("java") }
    apply { plugin("maven-publish") }

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
}

version = "0.0.1-SNAPSHOT"
tasks.compileJava {
    // use the project's version or define one directly
    options.javaModuleVersion.set(provider { project.version as String })
}

subprojects {

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    val PaxleonesBomVersion: String by project

    dependencies {
        dependencies {
            implementation(platform("com.paxleones:PaxleonesBom:$PaxleonesBomVersion"))
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}