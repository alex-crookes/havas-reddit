package com.alexcrookes.havas_redddit.provider.redditapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedItemImageCollectionDto(
	@SerialName("images")
	val images: List<RedditFeedImageDto>,
)
