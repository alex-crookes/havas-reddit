package com.alexcrookes.havas_redddit.provider.redditapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditImageDefinitionDto(
	@SerialName("url")
	val url: String,

	@SerialName("height")
	val height: Int,

	@SerialName("width")
	val width: Int,
)
