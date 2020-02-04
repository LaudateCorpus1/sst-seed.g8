ThisBuild / organization := "$organization$"

ThisBuild / turbo := true

lazy val commonSettings = BuildSettings.common ++ Seq(
  libraryDependencies ++= Seq(
    Dependencies.logbackClassic,
    Dependencies.slog4sApi,
    Dependencies.slog4sSl4j,
    Dependencies.scalaTest % Test,
    Dependencies.testContainers % Test,
    Dependencies.testContainersPostgres % Test
  ),
  dependencyOverrides += Dependencies.slf4jApi, // evicted by 1.8.0-beta4 from mdoc plugin
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
    name := "$name$",
    mdocIn := baseDirectory.value / "docs" / "mdoc",
    mdocOut := baseDirectory.value / "docs"
  )
  .enablePlugins(MdocPlugin)

addCommandAlias("check", "; scalafmtSbtCheck; scalafmtCheckAll; compile:scalafix --check; test:scalafix --check")
addCommandAlias("fix", "; compile:scalafix; test:scalafix; scalafmtSbt; scalafmtAll; mdoc")
