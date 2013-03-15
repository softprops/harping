organization := "me.lessis"

name := "harping"

version := "0.1.0-SNAPSHOT"

description := "http archival"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.1.0"

crossScalaVersions := Seq("2.9.1-1", "2.9.2", "2.10.0")

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.9.4" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"
