plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.abhijith.note_data_base"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "com.abhijith.note_data_base.config.CustomTestRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.test:runner:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    addHiltDependency()
    addRoomDependency()

    testImplementation ("com.google.dagger:hilt-android-testing:2.47")
    testAnnotationProcessor ("com.google.dagger:hilt-compiler:2.47")

    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.47")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.47")

    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.47")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.47")

    addArrowDependency()

    testImplementation ("org.junit.jupiter:junit-jupiter:5.8.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
    testImplementation ("com.google.truth:truth:1.1.3")

    implementation ("androidx.work:work-runtime-ktx:2.8.1")
    implementation ("androidx.hilt:hilt-work:1.0.0")
//    implementation ("androidx.startup:startup-runtime:1.1.1")
}