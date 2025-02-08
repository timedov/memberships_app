plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.performance)
}

android {
    namespace = "com.example.forboost"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.forboost"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)

    implementation(project(":core:common"))
    implementation(project(":core:data:api"))
    implementation(project(":core:data:impl:local"))
    implementation(project(":core:data:impl:firebase"))
    implementation(project(":core:ui"))
    implementation(project(":features:signin:api"))
    implementation(project(":features:signin:impl"))
    implementation(project(":features:commentreplies:api"))
    implementation(project(":features:commentreplies:impl"))
    implementation(project(":features:feed:api"))
    implementation(project(":features:feed:impl"))
    implementation(project(":features:profile:api"))
    implementation(project(":features:profile:impl"))
    implementation(project(":features:savepost:api"))
    implementation(project(":features:savepost:impl"))
    implementation(project(":features:postdetails:api"))
    implementation(project(":features:postdetails:impl"))
    implementation(project(":features:uploadpost:api"))
    implementation(project(":features:uploadpost:impl"))

    //dagger
    ksp(libs.dagger.compiler)
    implementation(libs.google.dagger)

    //compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)

    //retrofit
    implementation(libs.okhttp)
    implementation(libs.retrofit)

    //paging3
    implementation(libs.androidx.paging.common.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}