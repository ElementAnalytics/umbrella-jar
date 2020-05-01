name := "umbrella"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.elementanalytics" %% "datasets-server" % "0.4.0-0-719b0694-20200314-0447-SNAPSHOT",
  "com.elementanalytics" %% "datasets-labeling-server" % "0.2.2-1-b5134f57-20200414-2027-SNAPSHOT",
  "com.elementanalytics" %% "datasets-iceberg-server" % "0.2.1-3-a50669fa-20200409-1830-SNAPSHOT",
  "com.elementanalytics" %% "dataio-piagent" % "7.1.0-36-20ecdd6c-20200314-1730",
  "com.elementanalytics" %% "scheduler-service-api" % "1.1.1-3-f1b72abd-20200314-1653-SNAPSHOT",
  "com.elementanalytics" %% "workbench-backend" % "7.4.2-25-g8b07c378e-dirty"
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
