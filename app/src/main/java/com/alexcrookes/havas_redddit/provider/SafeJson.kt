package com.alexcrookes.havas_redddit.provider

import kotlinx.serialization.json.Json

val safeJson = Json {
	ignoreUnknownKeys = true
	isLenient = true
	encodeDefaults = true
}
