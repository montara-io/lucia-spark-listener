import sbt.Keys.libraryDependencies

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
    organization := "io.montara.lucia",
    crossScalaVersions := Seq("2.11.12", "2.12.12"),
    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3" % "provided",
    githubOwner := "montara-io",
    githubRepository := "luica-spark-listener",
    publishTo := Some(
      "GitHub montara-io Apache Maven Packages" at "https://maven.pkg.github.com/montara-io/lucia-spark-listener"
    ),
    publishMavenStyle := true,
    credentials += Credentials(
      "GitHub Package Registry",
      "maven.pkg.github.com",
      "montara-io",
      System.getenv("GITHUB_TOKEN")
    )
  )
  .settings(commonSettings: _*)
