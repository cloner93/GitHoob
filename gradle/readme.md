Managing dependencies in a single module project is pretty simple, but when you start scaling and adding modules you have different ways to handle this:
[link](https://proandroiddev.com/gradle-version-catalogs-for-an-awesome-dependency-management-f2ba700ff894)

- [*] Manual management: Involves having in many places the same string containing the libraries group, artifact and version. When a library needs to be updated, we need to change it manually in every Gradle file, this is very error-prone.
- [*] ext blocks: This is the recommended solution by Google in their documentation and solves the problems of manual management. The only downside is that the IDE doesn’t offer code completion.
- [*] buildSrc: In this approach, you use a special Gradle module and define your dependencies using Kotlin code. The major benefit of this option is auto-completion & navigation while the downside is that on any change to a version, the entire module needs to be recompiled.

In order to provide a standard for managing this, the Gradle team introduced version catalogs as an experimental feature in version 7.0 and promoted it to stable in version 7.4 🎉. This feature enables sharing dependencies in a centralized way, using an easy syntax and without **compromising the build speed.


for easy to do this ,I used [version-catalog-update-plugin](https://github.com/littlerobots/version-catalog-update-plugin). this is simple way to generate version catalog.    
./gradlew versionCatalogUpdate --interactive./gradlew versionCatalogUpdate --interactive

