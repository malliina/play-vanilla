import sbt.Keys.fork
import scala.sys.process.Process
import scala.util.Try

val vanilla = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
    organization := "com.malliina",
    version := "0.0.1",
    scalaVersion := "2.13.1",
    scalacOptions := Seq("-unchecked", "-deprecation"),
    pipelineStages := Seq(digest, gzip),
    libraryDependencies ++= Seq(
      "com.malliina" %% "okclient" % "1.14.0" % Test,
      "org.scalameta" %% "munit" % "0.7.1" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )

def gitHash: String =
  Try(Process("git rev-parse --short HEAD").lineStream.head).toOption.getOrElse("unknown")

Global / bloopExportJarClassifiers := Some(Set("sources"))
