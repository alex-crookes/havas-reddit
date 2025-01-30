import org.jetbrains.kotlin.config.JvmTarget

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.havas_redddit"
	compileSdk = libs.versions.sdk.compile.get().toInt()

	defaultConfig {
		applicationId = "com.alexcrookes.havas_redddit"
		minSdk = libs.versions.sdk.min.get().toInt()
		targetSdk = libs.versions.sdk.target.get().toInt()
		versionCode = 1
		versionName = "1.0.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = true
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
		jvmTarget = JvmTarget.JVM_17.toString()
	}
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(libs.hilt)
	implementation(libs.hilt.navigation.compose)
	ksp(libs.hilt.compiler)
	implementation(libs.kotlin.serialization.json)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.ktor.client)
	implementation(libs.ktor.client.android)
	implementation(libs.ktor.client.json)
	implementation(libs.ktor.client.negotiation)
	implementation(libs.glide.compose)

	// Legacy
	implementation (libs.androidx.recyclerview)
	implementation (libs.glide)

	// Test
	testImplementation(libs.junit)
	testImplementation(libs.kotlinx.coroutines.test)

	// Instrumented Tests
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
