scalaVersion := "2.12.10"

scalacOptions ++= Seq("-unchecked", "-deprecation")

// for vanilla
Seq(
  "com.typesafe.play" % "sbt-plugin" % "2.8.2",
  "com.typesafe.sbt" % "sbt-digest" % "1.1.4",
  "com.typesafe.sbt" % "sbt-gzip" % "1.0.2",
  "com.eed3si9n" % "sbt-buildinfo" % "0.9.0",
  "ch.epfl.scala" % "sbt-bloop" % "1.4.1",
  "org.scalameta" % "sbt-scalafmt" % "2.4.0"
) map addSbtPlugin
