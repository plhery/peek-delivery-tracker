# Play vitals are the app's only crash reports: keep line numbers for its de-obfuscated stack traces.
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# R8's missing_rules.txt: Play In-App Review's ktx names a Play services annotation that isn't on the classpath.
-dontwarn com.google.android.gms.common.annotation.NoNullnessRewrite

# Room makes each database's generated _Impl by reflection, through its no-argument constructor (WorkManager's
# WorkDatabase among them): kept here whatever rules the Room on the classpath brings.
-keep class * extends androidx.room.RoomDatabase { <init>(); }
