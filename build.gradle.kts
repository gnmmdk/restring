buildscript {

    repositories {
        maven {
            url = uri("http://172.16.6.241:8081/repository/maven-public/")
            isAllowInsecureProtocol = true
        }
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        maven {
            url = uri("http://172.16.6.241:8081/repository/maven-public/")
            isAllowInsecureProtocol = true
        }
        google()
        mavenCentral()
    }
}

subprojects {
    tasks.withType<Javadoc>().all { enabled = false }
}