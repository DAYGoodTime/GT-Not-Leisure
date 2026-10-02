plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

minecraft {
    extraRunJvmArguments.addAll("-Xmx8G", "-Xms8G", "-Dgtnhlib.dumpkeys=true")
}

tasks.withType<JavaCompile>().configureEach {
    options.annotationProcessorPath = configurations.annotationProcessor.get()
}

val runConfigs = listOf(
    "runClient" to "run/client",
    "runClient17" to "run/client_new",
    "runClient21" to "run/client_new",
    "runClient25" to "run/client_new",
    "runServer" to "run/server",
    "runServer17" to "run/server_new",
    "runServer21" to "run/server_new",
    "runServer25" to "run/server_new"
)

runConfigs.forEach { (taskName, path) ->
    tasks.named<JavaExec>(taskName) {
        workingDir = file("${projectDir}/$path")
        doFirst {
            workingDir.mkdirs()
        }
    }
}

listOf("runClient25", "runServer25").forEach { taskName ->
    tasks.named<JavaExec>(taskName) {
        // Optional and grouped injectors may legitimately match nothing, as in production runs.
        jvmArgumentProviders.add(org.gradle.process.CommandLineArgumentProvider {
            listOf("-Dmixin.debug.countInjections=false")
        })
    }
}

tasks.named<JavaExec>("runServer25") {
    // Angelica detects the side by client class presence; the merged development jar contains both sides.
    classpath = classpath.filter { !it.name.startsWith("Angelica-", ignoreCase = true) }
}
