package com.alexcrookes.havas_redddit.provider

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDto
import com.alexcrookes.havas_redddit.provider.redditapi.response.RedditFeedResponse

/**
 * Performs all requested operations with the Reddit Api
 *
 * In this example case, there is effectively only a single API call
 */
interface RedditApiProvider {
	suspend fun getHomePage(): Result<RedditFeedResponse>
}
