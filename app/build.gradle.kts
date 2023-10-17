import com.zaki.pintask.ProjectConfig

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.hilt.plugin.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
}

android {
    namespace = "com.zaki.pintask"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.zaki.pintask"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        testInstrumentationRunner = "com.zaki.pintask.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "DATABASE_NAME", "\"PinTask\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Androidx
    implementation(libs.androidx.ktx)
    implementation(libs.app.compat)
    implementation(libs.material)
    implementation(libs.constraint.layout)

    // Splash Api
    implementation(libs.splash.api)

    // Compose
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.icons)

    // Lifecycle
    implementation(libs.viewmodel.compose)
    implementation(libs.viewmodel.ktx)
    implementation(libs.livedata.ktx)
    implementation(libs.runtime.ktx)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)

    //Datastore
    implementation(libs.datastore)

    // Room Database
    implementation(libs.room.db)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    kaptAndroidTest(libs.room.compiler)
    androidTestAnnotationProcessor(libs.room.compiler)

    // Navigation
    implementation(libs.nav.compose)

    //Lottie
    implementation(libs.lottie.compose)

    // Unit Test
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    androidTestImplementation(libs.junit.androidx)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.arch.testing)
    androidTestImplementation(libs.google.truth)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}