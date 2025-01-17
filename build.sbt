name := "RedisClient"

organization := "net.debasishg"

version := "2.4.2"

crossScalaVersions := Seq("2.9.1", "2.9.0", "2.8.1", "2.8.0")

resolvers += "Twitter Repository" at "http://maven.twttr.com"

libraryDependencies <++= scalaVersion { scalaVersion =>
  // Helper for dynamic version switching based on scalaVersion
  val scalatestVersion: String => String = Map(("2.8.0" -> "1.3.1.RC2"), ("2.8.1" -> "1.5.1")) getOrElse (_, "1.6.1")
  // The dependencies with proper scope
  Seq(
    "commons-pool"   % "commons-pool"  % "1.5.6",
    "org.slf4j"      % "slf4j-api"     % "1.6.1",
    "org.slf4j"      % "slf4j-log4j12" % "1.6.1"  % "provided",
    "log4j"          % "log4j"         % "1.2.16" % "provided",
    "junit"          % "junit"         % "4.8.1"  % "test",
    "org.scalatest" %% "scalatest"     % scalatestVersion(scalaVersion) % "test",
    "com.twitter"    % "util"          % "1.11.4" % "test",
    "com.twitter"    % "finagle-core"  % "1.9.0" % "test",
    "thrift"         % "libthrift"     % "0.50" from "http://maven.twttr.com/thrift/libthrift/0.5.0/libthrift-0.5.0.jar"
  )
}

scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-Xcheckinit")

// TODO: Enable this with SBT 0.10.2 (See: https://github.com/harrah/xsbt/issues/147)
// scaladocOptions <++= (name, version) map { (name, ver) =>
//  Seq("-doc-title", name, "-doc-version", ver)
//}

//publishTo := Some("Scala-Tools Nexus Repository for Releases" at "http://nexus.scala-tools.org/content/repositories/releases")

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
