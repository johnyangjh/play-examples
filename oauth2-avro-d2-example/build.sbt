name := "oauth2-avro-d2-example"

version := "1.0.0-SNAPSHOT"

lazy val root = project in file(".") enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "me.tfeng.play-plugins" % "oauth2-plugin" % "0.3.1-SNAPSHOT",
  javaWs % "test",
  "me.tfeng.play-plugins" % "spring-test" % "0.3.1-SNAPSHOT" % "test"
)

AvroD2.settings
