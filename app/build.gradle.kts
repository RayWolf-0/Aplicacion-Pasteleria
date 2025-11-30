plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.apppasteleria"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apppasteleria"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Este runner es vital para que funcione el @RunWith(AndroidJUnit4::class) del profesor
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // ---------------------------------------------------------
    // COMPOSE (Gestionado por el BOM)
    // ---------------------------------------------------------
    implementation(platform("androidx.compose:compose-bom:2024.09.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material-icons-extended")

    // ---------------------------------------------------------
    // NAVEGACIÓN & FIREBASE
    // ---------------------------------------------------------
    implementation("androidx.navigation:navigation-compose:2.8.2")
    implementation(platform("com.google.firebase:firebase-bom:33.2.0"))
    implementation("com.google.firebase:firebase-auth-ktx")

    // ---------------------------------------------------------
    // IMÁGENES (Coil)
    // ---------------------------------------------------------
    implementation("io.coil-kt:coil-compose:2.6.0")

    // ---------------------------------------------------------
    // ROOM (Base de datos local)
    // ---------------------------------------------------------
    val room_version = "2.8.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version") // KSP es correcto aquí
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-paging:$room_version") // Paging opcional

    // Opcionales de Room que tenías (RxJava/Guava), descomenta si los usas:
    // implementation("androidx.room:room-rxjava2:$room_version")
    // implementation("androidx.room:room-rxjava3:$room_version")
    // implementation("androidx.room:room-guava:$room_version")

    // ---------------------------------------------------------
    // RED (Retrofit + OkHttp + Moshi)
    // ---------------------------------------------------------
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ---------------------------------------------------------
    // CORRUTINAS & LIFECYCLE
    // ---------------------------------------------------------
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    // =========================================================
    // TESTING
    // =========================================================

    // --- UNIT TESTS (Local - carpeta test/) ---
    // JUnit 4 (Legacy)
    testImplementation("junit:junit:4.13.2")
    // Kotest + JUnit 5
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    // MockK
    testImplementation("io.mockk:mockk:1.13.10")
    // Coroutines Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    // Room Test Helper
    testImplementation("androidx.room:room-testing:$room_version")


    // --- INSTRUMENTED TESTS (Android - carpeta androidTest/) ---
    // IMPORTANTE: Estas son las que usa el test de tu profesor

    // JUnit 4 para Android
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Compose Tests (Usando el BOM definido arriba o redefiniendo platform aquí)
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.02"))

    // Nota: Quitamos la versión 1.6.2 explicita para que use la del BOM compatible
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Herramientas de Debug (necesarias para tests y preview)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// Configuración para ejecutar tests unitarios con JUnit 5
tasks.withType<Test> {
    useJUnitPlatform()
}


