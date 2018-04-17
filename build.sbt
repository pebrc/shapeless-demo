import Dependencies._

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "pebrc",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "hello-shapeless",
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.3",
      scalaTest % Test
    )

  ).enablePlugins(JmhPlugin)
