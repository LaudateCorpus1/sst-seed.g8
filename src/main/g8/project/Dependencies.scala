import sbt._

object Dependencies {

  val doobie = "org.tpolecat" %% "doobie-postgres" % Versions.doobie
  val kindProjector = "org.typelevel" %% "kind-projector" % "0.10.3"
  val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val scalaTest = "org.scalatest" %% "scalatest" % "3.1.0"
  val scalazzi = "com.github.vovapolu" %% "scaluzzi" % "0.1.3"
  val silencer = "com.github.ghik" % "silencer-plugin" % Versions.silencer cross CrossVersion.full
  val silencerLib = "com.github.ghik" % "silencer-lib" % Versions.silencer cross CrossVersion.full
  val sortImports = "com.nequissimus" %% "sort-imports" % "0.3.2"
  val sstBundleZioHttp4sBlaze = "com.avast" %% "sst-bundle-zio-http4s-blaze" % Versions.sst
  val sstDoobieHikari = "com.avast" %% "sst-doobie-hikari" % Versions.sst
  val sstDoobieHikariPureConfig = "com.avast" %% "sst-doobie-hikari-pureconfig" % Versions.sst
  val sstFlywayPureConfig = "com.avast" %% "sst-flyway-pureconfig" % Versions.sst
  val sstHttp4sClientBlazePureConfig = "com.avast" %% "sst-http4s-client-blaze-pureconfig" % Versions.sst
  val sstHttp4sClientMonixCatcap = "com.avast" %% "sst-http4s-client-monix-catnap" % Versions.sst
  val sstJvm = "com.avast" %% "sst-jvm" % Versions.sst
  val sstMicrometerJmxPureConfig = "com.avast" %% "sst-micrometer-jmx-pureconfig" % Versions.sst
  val sstMonixCatnapPureConfig = "com.avast" %% "sst-monix-catnap-pureconfig" % Versions.sst
  val testContainers = "com.dimafeng" %% "testcontainers-scala-scalatest" % Versions.testContainers
  val testContainersPostgres = "com.dimafeng" %% "testcontainers-scala-postgresql" % Versions.testContainers
  val zio = "dev.zio" %% "zio" % "1.0.0-RC17"
  val zioInteropCats = "dev.zio" %% "zio-interop-cats" % "2.0.0.0-RC10"

  object Versions {
    val sst = "0.1.16"
    val silencer = "1.4.4"
    val doobie = "0.7.1"
    val testContainers = "0.35.0"
  }

}
