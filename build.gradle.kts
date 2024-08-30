plugins {
    java
}

group = "ru.vagapov"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    implementation("org.postgresql:postgresql:42.2.8")
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Final")

    testImplementation("junit:junit:4.13.2")

}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Test> {
        systemProperty("file.encoding", "UTF-8")
    }

    test {
        useJUnit()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
