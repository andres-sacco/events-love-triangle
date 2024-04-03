# Events and a love triangle

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

The project contains all the logic to have a real scenario about an application which use events.

## Table of contents

The following are the most important topics in this file:
- [Requirements](#Requirements)
- [Check requirements](#check-requirements)
- [Considerations](#considerations)
- [FAQ](#faq)

# Requirements

To use these tools you need to have in your machine the following things:
- [Java](http://jdk.java.net/)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

If you don't have some of these tools in your machine installed, please follow the instructions in the official documentation of each tool.

## Check requirements

If you have installed on your machine some of these tools previously or you installed all the tools now, please check if everything works fine.
- Check which version of Java you have using the following command:
   ````
   % java -version
  openjdk 17.0.6 2023-01-17 LTS
  OpenJDK Runtime Environment Microsoft-7209853 (build 17.0.6+10-LTS)
  OpenJDK 64-Bit Server VM Microsoft-7209853 (build 17.0.6+10-LTS, mixed mode, sharing)

   ````
- Check whether your Maven version is 3.8.0 or up. You can see which version of Maven you have using the following command:
   ````
   % mvn --version
   Apache Maven 3.8.3
   Maven home: /usr/share/maven
   ````
- Check whether the version of Docker in your machine is 18.09.0 or higher. You can check the version of Docker using the following command:

   ````
   % docker --version
  Docker version 24.0.2, build cb74dfc
   ````

## Considerations

To run all the microservices in the same machine, you need to consider that the following ports need to be available to use it:

| Name             | Application | Database    |
|------------------|-------------| ----------- |
| api-reservations | 8080        | 3312        |
| api-catalog      | 6070        | 3310        |


## FAQ

**Which version of the JDK can I use on this project?**

There is no restriction about which version, in particular, you need to consider that exist different alternatives to the JDK:
* **OracleJDK**: This version was free until Java 11, after this version you can use it for development/test environments, but you need to pay a license to use it on production. This version of the JDK offers to you the most recent patches of bugs and new features because Oracle is the owner of the language.


* **OpenJDK**: When Oracle bought Sun Microsystems created this as an open-source alternative that all the developers can use in any environment without restrictions. The main problem with this version is the patches of the bugs take time to appear in a case that is not critical.


Take into consideration that there are other alternatives but according to [Snyk 2021 Report](https://res.cloudinary.com/snyk/image/upload/v1623860216/reports/jvm-ecosystem-report-2021.pdf), most of the developers use OpenJDK.


**Which tools can I use for development?**

Many free tools support development. Here are two you may like:
- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) – IntelliJ is the most widely used IDE for  development.


- [Eclipse](https://www.eclipse.org/downloads/) – Eclipse is another IDE option for Kotlin development. Most plugins are free and have a vast community of developers who frequently update them.

Take into consideration that there are other IDE but according to [Snyk 2021 Report](https://res.cloudinary.com/snyk/image/upload/v1623860216/reports/jvm-ecosystem-report-2021.pdf), most of the JVM developers use Eclipse and Intellij, but the use of Visual Studio code is growing in the last year.


