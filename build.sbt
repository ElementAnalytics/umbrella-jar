name := "umbrella"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.elementanalytics" %% "datasets-server" % "1.0.0-31-75b4e969-20200710-1907-SNAPSHOT",
  "com.elementanalytics" %% "datasets-labeling-server" % "1.0.0-20-b6b88f32-20200710-1846-SNAPSHOT",
  "com.elementanalytics" %% "datasets-iceberg-server" % "1.0.0-6-5d0a3d12-20200710-2021-SNAPSHOT",
  "com.elementanalytics" %% "dataio-piagent" % "7.2.0-9-d40e5386",
  "com.elementanalytics" %% "scheduler-service-api" % "1.1.2-7-a1bfa111-SNAPSHOT"
)

/* Repository Definitions */
val nexus = "https://ean.jfrog.io"
val elementSnapshotRepo = "snapshots" at nexus + "/ean/sbt-dev-local"
val elementReleaseRepo = "releases" at nexus + "/ean/sbt-release-local"

val artifactory =
  Credentials.toDirect(Credentials(Path.userHome / ".sbt" / ".credentials"))

// Global exclude for logging dependencies to avoid multiple log binding warnings
val excludedLoggingLibs = Seq(
  "org.slf4j" % "slf4j-log4j12",
  "log4j" % "log4j"
)

resolvers := Seq(
  elementSnapshotRepo,
  elementReleaseRepo
)

resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"))

credentials += artifactory

excludeDependencies ++= excludedLoggingLibs
