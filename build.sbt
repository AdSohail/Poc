val sbtBuild = SBuild("sample", "words-poc", "0.0.1")
  .sourceDirectories("core", "model", "executor")

val wordsPoc = ( project in file(".") ).settings( sbtBuild.settings )
