## 2 Wrappers and practices to build
Maven and Gradle wrappers allows developers to use maven and gradle without installing it on their development machine.  
It is a script that invokes a declared version of one of this tool, and download it beforehand.  
Each solution allow to have a running project quickly and easily up to date with the last version!

### 2-1 Maven wrapper
[Maven wrapper](https://github.com/takari/maven-wrapper)

### 2-2 Gradle wrapper
[Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

### 2-3 Bom for Bill Of Materials
[BOM](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#bill-of-materials-bom-poms)
allows to define in a pom containing a dependency management element filled with dependecies and their versions.  
The BOM is used in others projects as a dependency management element.  
It allows to centralize the versions and even scope if you want (but this is not a good practice for the scope).

All your projects can used the same dependencies by using the bom you have defined.

Updating your dependencies is done by simply updating it in your bom.  
After that you just have to update the version of your bom in your projects.