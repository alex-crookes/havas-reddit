package com.alexcrookes.havas_redddit.provider.redditapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedItemDto(
	@SerialName("kind")
	val kind: String,

	@SerialName("data")
	val data: RedditFeedItemDataDto,
)
