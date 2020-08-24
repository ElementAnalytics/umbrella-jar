name := "umbrella"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.elementanalytics" %% "datasets-server" % "1.0.0-36-efc044cb-SNAPSHOT",
  "com.elementanalytics" %% "datasets-labeling-server" % "1.0.0-28-8d10873c-SNAPSHOT",
  "com.elementanalytics" %% "datasets-iceberg-server" % "1.0.0-11-62a0b3c3-SNAPSHOT",
  "com.elementanalytics" %% "dataio-piagent" % "7.2.0-11-072660e6-20200714-0731",
  "com.elementanalytics" %% "scheduler-service-api" % "1.1.2-8-49f7aaaf-SNAPSHOT",
  "com.redislabs" % "jredisgraph" % "2.0.2",
  "org.apache.commons" % "commons-csv" % "1.8"
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
