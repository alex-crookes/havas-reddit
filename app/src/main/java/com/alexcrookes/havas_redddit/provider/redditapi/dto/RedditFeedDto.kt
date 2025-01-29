package com.alexcrookes.havas_redddit.provider.redditapi.dto

data class RedditFeedDto(
	val after: String,
	val dist: Int,
	val modHash: String,
	val feedItems: List<RedditFeedItemDto>
)
