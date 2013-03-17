name := "HTTPMessage"

version := "0.0.0"

scalaVersion := "2.10.0"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies += "joda-time" % "joda-time"    % "2.1"

libraryDependencies += "org.joda" % "joda-convert" % "1.2"

libraryDependencies += "com.iinteractive" % "scala-test-more_2.10" % "0.01" % "test"

testFrameworks += new TestFramework("com.iinteractive.test.sbt.Framework")
