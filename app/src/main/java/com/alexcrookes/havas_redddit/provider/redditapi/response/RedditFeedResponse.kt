package com.alexcrookes.havas_redddit.provider.redditapi.response

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDataDto
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedResponse(
	@SerialName("kind")
	val kind: String,

	@SerialName("data")
	val data: RedditFeedDataDto
)

val RedditFeedResponse.asFeedDto: RedditFeedDto
	get() = RedditFeedDto(
		after = this.data.after,
		dist = this.data.dist,
		modHash = this.data.modhash,
		feedItems = this.data.children.map { it }
	)
