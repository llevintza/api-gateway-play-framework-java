ThisBuild / organization := "io.github.llevintza"
ThisBuild / crossScalaVersions := Seq("2.13.10")


ThisBuild / githubWorkflowJavaVersions := Seq(
  sbtghactions.JavaSpec.temurin("11"),
)
ThisBuild / githubWorkflowPublishTargetBranches := Seq()
ThisBuild / githubWorkflowBuild := Seq(
  WorkflowStep.Sbt(
    List(
      "clean",
// TODO: add the sbt coverage plugin to enable test coverage addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.9.3")
//      "coverage",
      "test",
// TODO: add the sbt coverage plugin to enable test coverage addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.9.3")
//      "coverageReport",
// TODO: add the sbt plugin to enable scala formatting: addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6") & addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
//      "scalafmtCheckAll"
    ),
    id = None,
    name = Some("Test")
  ),
  WorkflowStep.Use(
    UseRef.Public(
      "codecov",
      "codecov-action",
      "v1"
    )
  )
)

ThisBuild / githubWorkflowPublishTargetBranches :=
  Seq(
    RefPredicate.StartsWith(Ref.Tag("v")),
    RefPredicate.StartsWith(Ref.Tag("release/")),
    RefPredicate.Equals(Ref.Branch("main"))
  )

ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"
ThisBuild / sonatypeRepository := "https://s01.oss.sonatype.org/service/local"

inThisBuild(List(
  organization := "io.github.llevintza",
  homepage := Some(url("https://github.com/llevintza/api-gateway-play-framework-java")),
  // Alternatively License.Apache2 see https://github.com/sbt/librarymanagement/blob/develop/core/src/main/scala/sbt/librarymanagement/License.scala
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "llevintza",
      "Leo Levintza",
      "llevintza@gmail.com",
      url("https://github.com/llevintza")
    )
  ))
)

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(allSettings)
  .settings(
    name := """api-gateway""",
    //    version := appVersion,
    //    scalaVersion := "2.13.10",
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

val compilerOptions = Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-unchecked",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen"
)

//val reactiveMongoVersion = "1.0.7"
//val circeVersion = "0.14.1"

val scalaTestVersion = "3.2.11"
val scalaTestPlusVersion = "3.2.11.0"
val logstashVersion = "7.2"
val logbackVersion = "1.4.5"
val jacksonVersion = "2.14.2"

val baseSettings = Seq(
  scalacOptions ++= compilerOptions,
  scalacOptions ++= (
    if (priorTo2_13(scalaVersion.value))
      Seq(
        "-Xfuture",
        "-Yno-adapted-args",
        "-Ywarn-unused-import",
        "-Ypartial-unification"
      )
    else
      Seq(
        "-Ywarn-unused:imports"
      )
    ),
  Compile / console / scalacOptions ~= {
    _.filterNot(Set("-Ywarn-unused-import", "-Ywarn-unused:imports"))
  },
  Test / console / scalacOptions ~= {
    _.filterNot(Set("-Ywarn-unused-import", "-Ywarn-unused:imports"))
  },
  //  coverageHighlighting := true,
  //  (Compile / scalastyleSources) ++= (Compile / unmanagedSourceDirectories).value
)

val allSettings = baseSettings //++ publishSettings

def priorTo2_13(scalaVersion: String): Boolean =
  CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, minor)) if minor < 13 => true
    case _ => false
  }