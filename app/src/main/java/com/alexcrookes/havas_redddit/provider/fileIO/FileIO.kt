package com.alexcrookes.havas_redddit.provider.fileIO

import com.alexcrookes.havas_redddit.provider.safeJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Helper for reading/writiong files from resources package
 *
 * Suitable for Unit tests or just saving things as a blob...
 *
 * Can either read a Pure text
 *
 * Note: Write operations are removed
 */
interface FileIO {
	/**
	 * Reads a file from the Resources folder
	 *
	 * @param filename name of file to be read
	 *
	 * @return Optional String of the contents of the file
	 *
	 */
	fun readFileFromResources(filename: String): String?

	/**
	 * Reads a file from the Resources folder and deserializes it to return a Serializable class
	 *
	 * @param filename name of file to be read
	 * @param deserializer the serializer for the class
	 *
	 * @return Optional T of the contents of the file
	 */
	fun <T : Any> readClassFromResources(filename: String, deserializer: KSerializer<T>, jsonParser: Json = safeJson): T?
}
