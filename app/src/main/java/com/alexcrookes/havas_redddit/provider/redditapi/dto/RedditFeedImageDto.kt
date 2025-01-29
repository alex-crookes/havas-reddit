package com.alexcrookes.havas_redddit.provider.redditapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedImageDto(
	@SerialName("id")
	val id: String,

	@SerialName("source")
	val source: RedditImageDefinitionDto,

	@SerialName("resolutions")
	val resolutions: List<RedditImageDefinitionDto>,
)
