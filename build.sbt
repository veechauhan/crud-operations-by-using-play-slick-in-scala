name := """Crud-Operations-by-using-play-slick-in-Scala"""
organization := "Knoldus"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
resolvers += "Akka Snapshot Repository".at("https://repo.akka.io/snapshots/")

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies ++= Seq(
  "com.typesafe.play"    %% "play-slick"            % "5.0.0",
  "org.postgresql"        % "postgresql"            % "42.3.1",
  "com.typesafe.play"    %% "play-slick-evolutions" % "5.0.0",
  "com.github.jwt-scala" %% "jwt-play-json"         % "9.0.3",
  "com.github.tminglei"  %% "slick-pg"              % "0.20.2",
  "com.github.tminglei"  %% "slick-pg_play-json"    % "0.20.2"
)

