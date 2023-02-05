import sbt.Keys.libraryDependencies

inThisBuild(
  List(
    organization := "io.github.montara-io",
    homepage := Some(url("https://github.com/montara-io")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    developers := List(
      Developer(
        id = "avivvegh",
        name = "Aviv Vegh",
        email = "avivvegh85@gmail.com",
        url = url("https://github.com/montara-io")
      )
    )
  )
)

lazy val commonSettings = Seq(
  version := (if (git.gitCurrentBranch.value == "main") {
                "latest"
              } else {
                git.gitCurrentBranch.value
              }) + "-SNAPSHOT"
)

lazy val root = project
  .in(file("."))
  .aggregate(
    common,
    agent
  )
  .settings(
    publish / skip := true
  )

lazy val common = (project in file("common"))
  .settings(
    crossScalaVersions := Seq("2.11.12", "2.12.12"),
    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3" % "provided",
    publish / skip := true
  )

lazy val agent = (project in file("agent"))
  .settings(
    name := "sparklistener",
    organization := "io.github.montara-io",
    crossScalaVersions := Seq("2.11.12", "2.12.12"),
    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3" % "provided",
    publishTo := sonatypePublishToBundle.value,
    licenses := Seq(
      "APL2" -> url(
        "http://www.apache.org/licenses/LICENSE-2.0.txt"
      )
    ),
    homepage := Some(url("https://github.com/montara-io")),
    developers := List(
      Developer(
        id = "avivvegh",
        name = "Aviv Vegh",
        email = "avivvegh85@gmail.com",
        url = url("https://github.com/montara-io")
      )
    )
  )
  .settings(commonSettings: _*)
