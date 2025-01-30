package com.alexcrookes.havas_redddit.provider.redditapi

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDto

/**
 * Performs all requested operations with the Reddit Api
 *
 * In this example case, there is effectively only a single API call
 */
interface RedditApiProvider {
	suspend fun getHomePage(): Result<RedditFeedDto>
}
