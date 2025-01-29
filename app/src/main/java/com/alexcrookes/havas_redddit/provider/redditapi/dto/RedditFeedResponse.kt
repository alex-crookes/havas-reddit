package com.alexcrookes.havas_redddit.provider.redditapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedDataDto(
	@SerialName("after")
	val after: String,

	@SerialName("dist")
	val dist: Int,

	@SerialName("modhash")
	val modhash: String,

	@SerialName("geo_filter")
	val geoFilter: String?,

	@SerialName("children")
	val children: List<RedditFeedItemDto>,
)
