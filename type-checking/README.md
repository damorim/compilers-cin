This demo project serves to illustrate basic concepts in
type-checking.  We base our discussion in the following grammar.

// P = D ; E
// D = id : T
// T = char | integer | array [ num ] of T 
// E = literal | num | E mod E | E[E] | id

We are using the Gradle build system.  The build.gradle file is a
minimal build file, chracterizing main tasks of the build process.
The purpose is to simplify compilation and testing.  Most IDEs support
Gradle.  You can find detailed info about Gradle on the net.

The important tests are "build", "test", and "clean".  The following
command builds the project.

$> gradle build

enjoy,
-Marcelo
