publishMavenStyle := true
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
sonatypeProfileName := "io.github.montara-io"

import xerial.sbt.Sonatype._
homepage := Some(url("https://github.com/montara-io"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/montara-io/lucia-spark-listener"),
    "scm:git@github.com:montara-io/lucia-spark-listener.git"
  )
)

sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
sonatypeCredentialHost := "s01.oss.sonatype.org"

developers := List(
  Developer(
    id = "avivvegh",
    name = "Aviv Vegh",
    email = "avivvegh85@gmail.com",
    url = url("https://github.com/montara-io")
  )
)
