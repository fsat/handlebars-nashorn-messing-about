name := "handlebars-nashorn-messing-about"

enablePlugins(JavaServerAppPackaging)

libraryDependencies ++= List(
  "org.webjars" % "webjars-locator" % "0.30",
  "org.webjars" % "handlebars" % "4.0.2",
  "org.scalatest" %% "scalatest" % "2.2.4"
)

