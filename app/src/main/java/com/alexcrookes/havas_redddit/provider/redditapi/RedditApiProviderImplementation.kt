package com.alexcrookes.havas_redddit.provider.redditapi

import com.alexcrookes.havas_redddit.provider.RedditApiProvider
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDto
import com.alexcrookes.havas_redddit.provider.redditapi.response.RedditFeedResponse
import com.alexcrookes.havas_redddit.provider.safeJson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLBuilder
import io.ktor.serialization.kotlinx.json.json

class RedditApiProviderImplementation: RedditApiProvider {

	private val homePage = "https://www.reddit.com/.json"

	private lateinit var httpClient: HttpClient

	init {
		buildApiClient()
	}

	override suspend fun getHomePage(): Result<RedditFeedResponse> {
		val url = URLBuilder(homePage).build()
		val result = httpClient.get(url)

		return parseResult<RedditFeedResponse>(result)


	}


	// region HELPERS

	// @TODO - If needed, could add authentication plugins /etc.
	private fun buildApiClient() {
		httpClient = HttpClient {
			install(ContentNegotiation) { json(safeJson) }
		}
	}

	private suspend inline fun <reified T> parseResult(response: HttpResponse): Result<T> {
		return when (response.status) {
			HttpStatusCode.OK ->
				Result.success(response.body<T>())

			HttpStatusCode.NotFound ->
				Result.failure(Exception("Not Found"))

			else ->
				Result.failure(Exception("Other"))
		}
	}
	// endregion
}
