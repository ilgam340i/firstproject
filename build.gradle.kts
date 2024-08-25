import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    id("org.springframework.boot") version "3.0.1" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
    id("org.openapi.generator") version "6.4.0"
}

group = "ru.vagapov"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

configurations.implementation {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    exclude(group = "org.slf4j", module = "slf4j-simple")
    exclude(group = "ch.qos.logback", module = "logback-classic")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2022.0.3"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    implementation("org.postgresql:postgresql")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.ehcache:ehcache:3.10.8")

    //Swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.9")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
//    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("org.openapitools:openapi-generator:6.6.0")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")

    //Liquibase для БД
    implementation("org.liquibase:liquibase-core:4.24.0")

    //hibernate
    implementation("org.hibernate.orm:hibernate-jcache:6.4.4.Final")
//    implementation("org.hibernate:hibernate-core:6.5.2.Final")
//    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Final")

    //Common
//    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("javax.annotation:javax.annotation-api:1.3.2")
//    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
//    implementation("com.squareup.okhttp3:okhttp:4.10.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Mapstruct
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    dependencyLocking {
        lockAllConfigurations()
        lockFile.set(file("${rootDir}/gradle/dependency-locks/gradle-${parent?.let { "${it.name}-"} ?: ""}${project.name}.lockfile"))
    }
}

sourceSets["main"].java {
    srcDir("$buildDir/generated/userService/src/main/java")
}

sourceSets["test"].java {
    srcDir("$buildDir/generated/openapiclient/src/main/java")
}

tasks {
    val generateUserApi = register<GenerateTask>("generateUserApi") {
        generatorName.set("spring")
        inputSpec.set("$rootDir/openapi/out/user-service/user-service-api.yaml")
        outputDir.set("$buildDir/generated/userService")
        apiPackage.set("ru.vagapov.user.api")
        modelPackage.set("ru.vagapov.user.models")
        configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
                "interfaceOnly" to "true",
                "skipDefaultInterface" to "true",
                "useResponseEntity" to "false",
                "useSpringBoot3" to "true",
                "useJakartaEe" to "true",
                "library" to "spring-boot"
            )
        )
    }
    val generateUserTestApi = register<GenerateTask>("generateUserTestApi") {
        generatorName.set("java")
        inputSpec.set("$rootDir/openapi/out/user-service/user-service-api.yaml")
        outputDir.set("$buildDir/generated/openapiclient")
        apiPackage.set("ru.vagapov.user.test_client.api")
        invokerPackage.set("ru.vagapov.user.test_client.invoker")
        modelPackage.set("ru.vagapov.user.test_client.models")
        generateApiTests.set(false)
        generateApiDocumentation.set(false)
        generateModelTests.set(false)
        library.set("native")
        configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
            )
        )
    }

    compileJava {
        dependsOn(
            generateUserApi
        )
    }

    compileTestJava {
        dependsOn(
            generateUserTestApi
        )
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Test> {
        systemProperty("file.encoding", "UTF-8")
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    register("resolveAndLockAll") {
        doLast {
            configurations.filter {
                it.isCanBeResolved
            }.forEach {it.resolve()}
        }
    }

    register("newPatch") {
        description = "Создание sql патча"
        var currentVersion = ""

        doFirst {
            if (!project.hasProperty("patchname")) {
                throw IllegalArgumentException("Property patchname not provided")
            }
            val patchname = project.property("patchname")
            currentVersion = if (project.hasProperty("releaseversion")) {
                project.property("releaseversion").toString()
            } else {
                try {
                    project.property("version").toString().split("\\_")[0]
                } catch (e: Exception) {
                    throw GradleException()
                }
            }

            val path = "${projectDir}/src/main/resources/db/changelog"
            val patchDirectory = "sql/${currentVersion}"
            val patchPath = "${path}/${patchDirectory}"

            val folder = File(patchPath)
            if (!folder.exists()) {
                folder.mkdirs()
            }

            val patchTs = System.currentTimeMillis()

            val updateFileName = "${patchTs}_${patchname}.sql"
            val updateFilePath = "${patchPath}/${updateFileName}"

            val patchText = "-- liquibase formatted sql"
            File(updateFilePath).writeText(patchText, Charsets.UTF_8)
            println("file ${updateFileName} was created")

            val changelog = "changelog.yaml"
            val changelogFilePath = "${patchPath}/${changelog}"
            val mainChangeLogFilePath = "${path}/${changelog}"
            val file = File(changelogFilePath)
            var changelogText =
                """  - include:
          file: ${updateFileName}
          relativeToChangelogFile: true
                """
            if (file.length().toInt() == 0) {
                var mainChangeLogText =
                    """  - include:
          file: ${patchDirectory}/${changelog}
          relativeToChangelogFile: true
                    """

                val mainChangeLogFile = File(mainChangeLogFilePath)
                if (mainChangeLogFile.length().toInt() == 0) {
                    mainChangeLogFile.appendText("databaseChangeLog:\n${mainChangeLogText}", Charsets.UTF_8)
                } else {
                    if (!mainChangeLogFile.readText(Charsets.UTF_8).endsWith("\n")) {
                        mainChangeLogText = "\n" + mainChangeLogText
                    }
                    mainChangeLogFile.appendText(mainChangeLogText, Charsets.UTF_8)
                }
                file.appendText("databaseChangeLog:\n${changelogText}", Charsets.UTF_8)
            } else {
                if (!file.readText(Charsets.UTF_8).endsWith("\n")) {
                    changelogText = "\n" + changelogText
                }
                file.appendText(changelogText, Charsets.UTF_8)
            }
        }

    }
}
