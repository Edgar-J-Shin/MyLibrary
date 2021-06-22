object Deps {

    object TestLib {
        const val junit = "junit:junit:${Versions.junit}"
    }

    object AndroidTestLib {
        const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val googleServices = "com.google.gms:google-services:${Versions.googlePlayServicesGradle}"
    }

    object Lib {

        object Kotlin {
            const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        }

        /**
         * AndroidX [https://developer.android.com/jetpack/androidx/versions]
         */
        object Android {
            const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
            const val material = "com.google.android.material:material:${Versions.material}"
            const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
            const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        }

        /**
         * Jetpack Lifecycle [https://developer.android.com/jetpack/androidx/releases/lifecycle]
         */
        object Lifecycle {
            const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        /**
         * Startup [https://developer.android.com/topic/libraries/app-startup]
         */
        object Startup {
            const val startup = "androidx.startup:startup-runtime:${Versions.startup}"
        }

        /**
         * Room [https://developer.android.com/training/data-storage/room]
         */
        object Room {
            const val room = "androidx.room:room-runtime:${Versions.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.room}"
            const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        }

        /**
         * Hilt [https://developer.android.com/training/dependency-injection/hilt-android]
         */
        object Hilt {
            const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        }

        /**
         * Reactive X
         * RxJava [https://github.com/ReactiveX/RxJava]
         * RxAndroid [https://github.com/ReactiveX/RxAndroid]
         * RxBinding [https://github.com/JakeWharton/RxBinding]
         */
        object Rx {
            const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
            const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
            const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
        }

        /**
         * Gson [https://github.com/google/gson]
         */
        object Json {
            const val gson = "com.google.code.gson:gson:${Versions.gson}"
        }

        /**
         * Network
         * Retrofit [https://square.github.io/retrofit/]
         * OkHttp [https://square.github.io/okhttp/]
         */
        object Network {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
            const val okhttpBOM = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp}"
            const val okhttp = "com.squareup.okhttp3:okhttp"
            const val loggingIntercepter = "com.squareup.okhttp3:logging-interceptor"
            const val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"
        }

        /**
         * Glide [https://bumptech.github.io/glide/]
         * GlidePalette [https://github.com/florent37/GlidePalette]
         * GlideTransformations [https://github.com/wasabeef/glide-transformations]
         * PhotoView [https://github.com/Baseflow/PhotoView]
         */
        object Glide {
            const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
            const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
            const val palette = "com.github.florent37:glidepalette:${Versions.glidePalette}"
            const val transformations = "jp.wasabeef:glide-transformations:${Versions.glideTransformations}"
        }

        object CustomUI {
            const val transformationLayout = "com.github.skydoves:transformationlayout:${Versions.transformationLayout}"
            const val recyclerViewDivider = "com.github.fondesa:recycler-view-divider:${Versions.recyclerViewDivider}"
            const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
            const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"
            const val avLoadingIndicatorView = "com.wang.avi:library:${Versions.avLoadingIndicatorView}"
        }

        /**
         * Timber [https://github.com/JakeWharton/timber]
         * TedPermission [https://github.com/ParkSangGwon/TedPermission]
         * LeakCanary [https://square.github.io/leakcanary/]
         * */
        object Etc {
            const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
            const val tedPermission = "gun0912.ted:tedpermission:${Versions.tedPermission}"
            const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
        }
    }
}