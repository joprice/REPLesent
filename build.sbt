/*
 * Copyright 2015 Marconi Lanna
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
name := "REPLesent"

version := "1.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
    "-deprecation"            // Emit warning and location for usages of deprecated APIs
  , "-encoding", "UTF-8"      // Specify character encoding used by source files
  , "-feature"                // Emit warning and location for usages of features that should be imported explicitly
//  , "-language:_"             // Enable or disable language features (see list below)
//  , "-optimise"               // Generates faster bytecode by applying optimisations to the program
  , "-target:jvm-1.7"         // Target platform for object files
  , "-unchecked"              // Enable additional warnings where generated code depends on assumptions
// Doesn't play well with ScalaTest
//  , "-Xdev"                   // Indicates user is a developer - issue warnings about anything which seems amiss
  , "-Xfatal-warnings"        // Fail the compilation if there are any warnings
  , "-Xlint:_,-nullary-unit"  // Enable or disable specific warnings (see list below)
  , "-Yno-adapted-args"       // Do not adapt an argument list to match the receiver
  , "-Ywarn-dead-code"        // Warn when dead code is identified
// Not really useful
//  , "-Ywarn-numeric-widen"    // Warn when numerics are widened
  , "-Ywarn-unused"           // Warn when local and private vals, vars, defs, and types are are unused
  , "-Ywarn-unused-import"    // Warn when imports are unused
  , "-Ywarn-value-discard"    // Warn when non-Unit expression results are unused
)

scalacOptions in (Compile, console) := Seq.empty

scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value

libraryDependencies ++= Seq(
    "org.scala-lang"  % "scala-compiler" % "2.11.7" % Compile
  , "org.scalatest"  %% "scalatest"      % "2.2.6"  % Test
)

// Improved incremental compilation
incOptions := incOptions.value.withNameHashing(true)

// Improved dependency management
updateOptions := updateOptions.value.withCachedResolution(true)

// Download and create Eclipse source attachments for library dependencies
// EclipseKeys.withSource := true

// Uncomment to enable offline mode
// offline := true

showSuccess := true

showTiming := true

shellPrompt := { state =>
  import scala.Console.{CYAN,RESET}
  val p = Project.extract(state)
  val name = p.getOpt(sbt.Keys.name) getOrElse p.currentProject.id
  s"[$CYAN$name$RESET] $$ "
}
