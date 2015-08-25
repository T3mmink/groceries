name := "groceries"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "ReactiveCouchbase Releases" at "https://raw.github.com/ReactiveCouchbase/repository/master/releases/"
resolvers += "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"

val akkaVersion = "2.3.6"
val sprayVersion = "1.3.1"

libraryDependencies ++= Seq(  "org.reactivecouchbase" %% "reactivecouchbase-core" % "0.3"
  ,"org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  ,"com.github.nscala-time" %% "nscala-time" % "1.4.0"
  // -- testing --
  , "org.scalamock" %% "scalamock-scalatest-support" % "3.1.4" % "test"
  // -- Logging --
  ,"ch.qos.logback" % "logback-classic" % "1.1.2"
  // -- Akka --
  ,"com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
  ,"com.typesafe.akka" %% "akka-actor" % akkaVersion
  ,"com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  // -- Spray --
  ,"io.spray" %% "spray-routing" % sprayVersion
  ,"io.spray" %% "spray-can" % sprayVersion
  ,"io.spray" %% "spray-httpx" % sprayVersion
  ,"io.spray" %% "spray-testkit" % sprayVersion % "test"
  // -- Json --
  ,"org.json4s" %% "json4s-native" % "3.2.11"
  ,"com.typesafe.play" %% "play-json" % "2.4.0-M1"
)
