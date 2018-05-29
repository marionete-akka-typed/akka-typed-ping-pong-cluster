lazy val commonSettings = Seq(
  organization := "uk.co.marionete",
  scalaVersion := "2.12.6",
  version := "0.1.0-SNAPSHOT"
)
lazy val dependencies = Seq(
  // https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-typed
  "com.typesafe.akka" %% "akka-actor-typed" % "2.5.12",
  // https://mvnrepository.com/artifact/org.scalatest/scalatest
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  // https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit-typed
  "com.typesafe.akka" %% "akka-testkit-typed" % "2.5.12" % Test
)
lazy val root = (project in file(".")).
  settings(
    commonSettings,
    // set the main class for packaging the main jar
    mainClass in (Compile, packageBin) := Some("uk.co.marionete.ping_pong.Main"),
    name := "AkkaTypedPingPong",
    libraryDependencies ++= dependencies
  )

  // Simple and constant jar name
  assemblyJarName in assembly := s"akka-typed-ping-pong-cluster.jar"
