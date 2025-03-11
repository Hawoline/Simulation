plugins {
  id("java")
}

group = "org.hawoline"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
  useJUnitPlatform()
}