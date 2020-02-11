Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / turbo := true
ThisBuild / organization := "$organization$"

lazy val commonSettings = BuildSettings.common ++ Seq(
  libraryDependencies ++= Seq(
    Dependencies.logbackClassic,
    Dependencies.scalaTest % Test,
    Dependencies.testContainers % Test,
    Dependencies.testContainersPostgres % Test
  ),
  Test / publishArtifact := false
)

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.sstBundleZioHttp4sBlaze,
      Dependencies.sstHttp4sClientBlazePureConfig,
      Dependencies.sstHttp4sClientMonixCatcap,
      Dependencies.sstMonixCatnapPureConfig,
      Dependencies.sstDoobieHikariPureConfig,
      Dependencies.sstDoobieHikari,
      Dependencies.sstFlywayPureConfig,
      Dependencies.sstJvm,
      Dependencies.sstMicrometerJmxPureConfig,
      Dependencies.doobie
    ),
    name := "$name$"
  )

addCommandAlias("checkAll", "; scalafmtSbtCheck; scalafmtCheckAll; compile:scalafix --check; test:scalafix --check; test")
addCommandAlias("fixAll", "; compile:scalafix; test:scalafix; scalafmtSbt; scalafmtAll")
