plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.sdk.blokid"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    publishing {
        singleVariant("release") {
            withSourcesJar() // Optionally include sources JAR
            withJavadocJar() // Optionally include Javadoc JAR
        }
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
publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "com.github.mprogrammer2020"
            artifactId = "BlokID"
            version = "1.1"

            pom {
                name.set("BlokID") // Replace with your SDK name
                description.set("A description of your SDK.")
                url.set("https://github.com/Mprogrammer2020/BlokID-Android-Netset")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("mprogrammer2020")
                        name.set("Mithilesh Kumar") // Replace with your name
                        email.set("mithilesh.kumar@netsetsoftware.com") // Replace with your email
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/Mprogrammer2020/BlokID-Android-Netset.git")
                    developerConnection.set("scm:git:ssh://git@github.com:Mprogrammer2020/BlokID-Android-Netset.git")
                    url.set("https://github.com/Mprogrammer2020/BlokID-Android-Netset")
                }
            }
        }
    }
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Networking
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // Retrofit Gson Converter
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")// OkHttp
    implementation ("com.google.code.gson:gson:2.10.1") // Gson
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0") // OkHttp Logging Interceptor
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0") // Retrofit RxJava2 Adapter
    implementation ("io.socket:socket.io-client:2.0.1") // Socket.IO Client

//    implementation ("com.applovin.sdk:applovin-sdk:11.2.0")
    implementation ("com.unity3d.ads:unity-ads:4.7.0")
    implementation ("com.applovin:applovin-sdk:+")
}



