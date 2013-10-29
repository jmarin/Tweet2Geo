name := "Tweet2Geo"

version := "0.1.0"

scalaVersion := "2.10.3"

scalacOptions += "-deprecation"

seq(Revolver.settings: _*)

scalariformSettings

org.scalastyle.sbt.ScalastylePlugin.Settings

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases",
  "OpenGeo Repository" at "http://repo.opengeo.org",
  "Spray Repository" at "http://repo.spray.io"
)

libraryDependencies ++= {
  val twitter4jVersion = "3.0.3"
  val akkaVersion = "2.2.3"
  val sprayVersion = "1.2-RC1"
  val sprayJsonVersion = "1.2.5"
  Seq(
    "org.twitter4J" % "twitter4j-stream" % twitter4jVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "io.spray" % "spray-can" % sprayVersion,
    "io.spray" % "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % sprayJsonVersion,
    "io.spray" % "spray-testkit" % sprayVersion % "test"
  )
}
