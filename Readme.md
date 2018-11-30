# General Exception Plugin

## Dependencies

It is recommended that this custom plugin be used on ```openjdk 8``` and with error prone version 2.3.2.

## Example

[Examples]( https://github.com/google/error-prone/tree/master/examples/plugin) of how a custom plugin is implemented is provided on the [Error Prone Github](https://github.com/google/error-prone) and on the [Documentation Website]( https://errorprone.info/docs/plugins).

## Maven

In this Readme the steps for how to implement our custom bug for Maven are provided below.

### Installation

Git clone the repository.

Add the error prone custom check to the same directory as the program to the tested.

Create a pom file in the same directory as the the program to be tested and the newly added custom checker. Below is an example pom.xml. Note that the groupID, version, and specified modules will need to be changed to match your specific program. 

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>

  <modules>
    <module>general-exception-check</module>
    <module>your-program</module>
  </modules>

  <packaging>pom</packaging>
  
</project>

```

Edit the pom file of the program to be checked to connect it to a parent pom file. An example of how to do this is below:

```
<parent>
	<artifactId>parent</artifactId>
	<groupId>com.example</groupId>
	<version>1.0-SNAPSHOT</version>
</parent>
```

Edit the pom file of the program to be checked to include the custom check in the dependencies. Below is an example build:

```
<build>
<plugins>
  <!-- Turn on Error Prone -->
  <plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>3.5.1</version>
	<configuration>
	  <compilerId>javac-with-errorprone</compilerId>
	  <forceJavacCompilerUse>true</forceJavacCompilerUse>
	  <!-- maven-compiler-plugin defaults to targeting Java 5 -->
	  <source>1.8</source>
	  <target>1.8</target>
	  <!-- Add custom checks to the annotation processor classpath.
		   Note that Maven doesn't guarantee the build order and could
		   attempt to build hello before sample-plugin, and fail because it
		   cannot find sample-plugin.
		   One workaround is to add sample-plugin as a dependency (with
		   scope provided so it's not transitive). -->
	  <annotationProcessorPaths>
		<path>
		  <groupId>com.example</groupId>
		  <artifactId>general-exception-check</artifactId>
		  <version>1.0-SNAPSHOT</version>
		</path>
	  </annotationProcessorPaths>
	</configuration>
	<dependencies>
	  <dependency>
		<groupId>org.codehaus.plexus</groupId>
		<artifactId>plexus-compiler-javac-errorprone</artifactId>
		<version>2.5</version>
	  </dependency>
	  <!-- override plexus-compiler-javac-errorprone's dependency on
		   Error Prone with the latest version -->
	  <dependency>
		<groupId>com.google.errorprone</groupId>
		<artifactId>error_prone_core</artifactId>
		<version>2.3.2</version>
	  </dependency>
	</dependencies>
  </plugin>
</plugins>
</build>

```

### Utilization

To use the custom checker, from a terminal, go to the directory containing your program, the added general-exception-check, and the new parent pom.xml.

Assuming [Maven is installed](https://maven.apache.org/install.html) enter the following command:

```
mvn compile
```

An example of the report upon the custom checker finding a bug would be:

```
java:33: error: [GeneralExceptionCheck] Catch general exception not specific enough.
       } catch (Exception E) {
                          ^
```
