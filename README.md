
##This repository contains all the samples files for Urovo Android Smart POS Payment Terminal;Handheld Data Terminal, including the revision history.
##Need more information about getting started with Urovo Android device? Check the [official docs][getting-started].

> 如何使用SDK依赖项目

>Add it in your root build.gradle at the end of repositories:
~~~JAVA

allprojects {
   repositories {
   ...
   maven { url 'https://jitpack.io' }
   }
}

~~~
> Add the dependency
~~~JAVA

dependencies {
    implementation 'com.github.urovosamples:usdk:4.1.0'
}
~~~