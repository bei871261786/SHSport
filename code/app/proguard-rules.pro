# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Developtool\google\adt-bundle-windows-x86_64_20140101\sdk/tools/proguard/proguard-android.txt
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
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
 -dontskipnonpubliclibraryclasses # 不忽略非公共的库类
 -optimizationpasses 5            # 指定代码的压缩级别
 -dontusemixedcaseclassnames      # 是否使用大小写混合
 -dontpreverify                   # 混淆时是否做预校验
 -verbose                         # 混淆时是否记录日志
 -keepattributes *Annotation*     # 保持注解
 -ignorewarning                   # 忽略警告
 -dontoptimize                    # 优化不优化输入的类文件

 -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

 #保持哪些类不被混淆
 -keep public class * extends android.app.Activity
 -keep public class * extends android.app.Application
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.app.backup.BackupAgentHelper
 -keep public class * extends android.preference.Preference
 -keep public class com.android.vending.licensing.ILicensingService

 #生成日志数据，gradle build时在本项目根目录输出
 -dump class_files.txt            #apk包内所有class的内部结构
 -printseeds seeds.txt            #未混淆的类和成员
 -printusage unused.txt           #打印未被使用的代码
 -printmapping mapping.txt        #混淆前后的映射

 -keep public class * extends android.support.** #如果有引用v4或者v7包，需添加
# -libraryjars libs/xxx.jar        #混淆第三方jar包，其中xxx为jar包名
# -keep class com.xxx.**{*;}       #不混淆某个包内的所有文件
# -dontwarn com.xxx**              #忽略某个包的警告
 -keepattributes Signature        #不混淆泛型
 -keepnames class * implements java.io.Serializable #不混淆Serializable

#----------------不混淆资源类--------------------
-keepclassmembers class **.R$* {
    public static <fields>;
}
 -keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
     native <methods>;
 }
 -keepclasseswithmembers class * {      # 保持自定义控件类不被混淆
     public <init>(android.content.Context, android.util.AttributeSet);
 }
 -keepclasseswithmembers class * {      # 保持自定义控件类不被混淆
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }
 -keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
     public void *(android.view.View);
 }
 -keepclassmembers enum * {             # 保持枚举 enum 类不被混淆
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }
 -keep class * implements android.os.Parcelable {         # 保持 Parcelable 不被混淆
     public static final android.os.Parcelable$Creator *;
 }
 # Gson
# --------------Gson混淆--------------------
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class shlottery.gov.cn.lotterycenter.bean.** { *; }

 # 使用Gson时需要配置Gson的解析对象及变量都不混淆。不然Gson会找不到变量。
 # 将下面替换成自己的实体类

 # ButterKnife
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }


 # EventBus
 -keepattributes *Annotation*
 -keepclassmembers class ** {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }

 # Picasso
 -dontwarn com.squareup.picasso
 -keep class com.squareup.picasso.**{*;}



  -dontwarn cn.jpush.**
  -keep class cn.jpush.** { *; }

-keep class com.google.common.io.Resources {
    public static <methods>;
}
-keep class com.google.common.collect.Lists {
    public static ** reverse(**);
}
-keep class com.google.common.base.Charsets {
    public static <fields>;
}

-keep class com.google.common.base.Joiner {
    public static com.google.common.base.Joiner on(java.lang.String);
    public ** join(...);
}

-keep class com.google.common.collect.MapMakerInternalMap$ReferenceEntry
-keep class com.google.common.cache.LocalCache$ReferenceEntry

# http://stackoverflow.com/questions/9120338/proguard-configuration-for-guava-with-obfuscation-and-optimization
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# Guava 19.0
-dontwarn java.lang.ClassValue
-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#百度地图
-dontwarn com.baidu.**
-keep class com.baidu.** {*;}
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}


#友盟分享
-dontwarn com.umeng.**
-keep class com.umeng*.** {*;}
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*

#微信分享
-dontwarn com.tencent.**
-keep class com.tencent.** { *; }
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}

# OkHttp3
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }