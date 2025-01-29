package com.alexcrookes.havas_redddit.provider.redditapi.response

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDataDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedResponse(
	@SerialName("kind")
	val kind: String,

	@SerialName("data")
	val data: RedditFeedDataDto
)
