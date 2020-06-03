import sbt.Keys.fork
import scala.sys.process.Process
import scala.util.Try

val vanilla = project
  .in(file("."))
  .enablePlugins(PlayScala, JavaServerAppPackaging, SystemdPlugin)
  .settings(
    organization := "com.malliina",
    version := "0.0.1",
    scalaVersion := "2.13.2",
    scalacOptions := Seq("-unchecked", "-deprecation"),
    pipelineStages := Seq(digest, gzip),
    libraryDependencies ++= Seq(
      "com.malliina" %% "okclient" % "1.17.0" % Test,
      "org.scalameta" %% "munit" % "0.7.7" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    maintainer := "Firstname Lastname <email@address.com>",
    sources in (Compile, doc) := Seq.empty
  )

def gitHash: String =
  Try(Process("git rev-parse --short HEAD").lineStream.head).toOption.getOrElse("unknown")

Global / bloopExportJarClassifiers := Some(Set("sources"))
