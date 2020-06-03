import sbt.Keys.fork
import scala.sys.process.Process
import scala.util.Try

val vanilla = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
    organization := "com.malliina",
    version := "0.0.1",
    scalaVersion := "2.13.2",
    scalacOptions := Seq("-unchecked", "-deprecation"),
    pipelineStages := Seq(digest, gzip),
    libraryDependencies ++= Seq(
      "com.malliina" %% "okclient" % "1.17.0" % Test,
      "org.scalameta" %% "munit" % "0.7.8" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    sources in (Compile, doc) := Seq.empty,
    dockerBaseImage := "openjdk:11",
    dockerExposedPorts ++= Seq(9000),
    daemonUser in Docker := "vanilla"
  )

def gitHash: String =
  Try(Process("git rev-parse --short HEAD").lineStream.head).toOption.getOrElse("unknown")

Global / bloopExportJarClassifiers := Some(Set("sources"))
