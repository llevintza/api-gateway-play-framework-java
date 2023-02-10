val appVersion = "1.0.0"
val logstashVersion = "7.2"
val logbackVersion = "1.4.5"
val jacksonVersion = "2.14.2"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := """api-gateway""",
    version := appVersion,
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      guice,
      "net.logstash.logback" % "logstash-logback-encoder" % logstashVersion,
      "ch.qos.logback" % "logback-access" % logbackVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion,
      "ch.qos.logback" % "logback-core" % logbackVersion,
      "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
      "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
      "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
      "com.fasterxml.jackson.module" % "jackson-module-paranamer" % jacksonVersion
    )
  )