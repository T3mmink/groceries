name := "groceries"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "ReactiveCouchbase Releases" at "https://raw.github.com/ReactiveCouchbase/repository/master/releases/"
resolvers += "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(  "org.reactivecouchbase" %% "reactivecouchbase-core" % "0.3")
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"