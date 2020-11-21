
##下拉刷新第三方框架

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
    implementation 'com.github.Airomantic:RefreshSDK:v1.0.0'
}
~~~