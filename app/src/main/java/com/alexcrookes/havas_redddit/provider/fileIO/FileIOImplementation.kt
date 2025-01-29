package com.alexcrookes.havas_redddit.provider.fileIO

import com.alexcrookes.havas_redddit.provider.safeJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json


class FileIOImplementation: FileIO {
	override fun readFileFromResources(filename: String): String? = getFile(filename)

	override fun <T : Any> readClassFromResources(
		filename: String, deserializer: KSerializer<T>, jsonParser: Json
	): T? {

		val fileContents = getFile(filename) ?: return null

		return safeJson.decodeFromString(deserializer, fileContents)
	}

	// endregion


	// region Helpers
	private fun getFile(filename: String): String? {
		javaClass.getResourceAsStream(filename)?.let { stream ->
			val stringValue = stream.bufferedReader().use { it.readText() }
			stream.close()
			return stringValue
		}

		return null
	}

	// Write methods are removed
}
