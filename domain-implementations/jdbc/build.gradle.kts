plugins {
    id("buildlogic.java-library-conventions")
}

dependencies{

    implementation(project(":domain"))
    implementation(project(":utilities"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation(project(":utilities"))
    testImplementation(project(":domain"))

    implementation ("org.junit.jupiter:junit-jupiter-api:5.10.0")
    implementation ("org.junit.jupiter:junit-jupiter:5.10.0")
}