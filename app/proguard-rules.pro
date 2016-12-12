# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#指定压缩级别（在0~7之间，默认为5，一般不需要修改）
-optimizationpasses 5

#混淆时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames

#指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses

#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

#不做预校验(preverify是proguard的4个步骤之一)
#去掉预校验，加快混淆速度
-dontpreverify

#混淆后生成映射文件
#类名 -> 混淆后类名的映射关系
#printmapping指定映射文件的名称
-verbose
-printmapping proguardMapping.txt

#混淆时采用的算法，后面的参数是一个过滤器，一般不做改变
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护代码中的Annotation（注释）不被混淆
-keepattributes *Annotation*

#保留代码行号
-keepattributes SourceFile,LineNumberTable

#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**