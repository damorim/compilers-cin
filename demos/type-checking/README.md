This demo project serves to illustrate basic concepts in
type-checking.  We base our discussion in the following grammar.

  P = D ; E 
  D = id : T
  T = char | integer | array [ num ] of T | T -> T
  E = literal | num | E mod E | E[E] | id | E(E)

Example programs in this language:

Example 1:

  x : integer;
  x mod 5

Example 2:

  x : array [ 2 ] of integer;
  myFun (x[2])

Example 3:

  myFun : integer -> integer;
  myFun(5)

We are using the Gradle build system.  The build.gradle file is a
minimal build file, chracterizing main tasks of the build process.
The purpose is to simplify compilation and testing.  Most IDEs support
Gradle.  You can find detailed info about Gradle on the net.

The important tests are "build", "test", and "clean".  The following
command builds the project.

$> gradle build

enjoy,
-Marcelo
