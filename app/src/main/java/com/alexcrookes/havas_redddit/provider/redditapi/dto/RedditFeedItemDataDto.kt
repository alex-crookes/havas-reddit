package com.alexcrookes.havas_redddit.provider.redditapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedItemDataDto(
	@SerialName("name")
	val itemName: String,

	@SerialName("created")
	val created: Double, // This data is technically LONG in the API response, but fails on occasion

	@SerialName("subreddit")
	val subreddit: String,

	@SerialName("title")
	val title: String,

	@SerialName("selftext")
	val body: String,

	@SerialName("ups")
	val upVotes: Int,

	@SerialName("downs")
	val downVotes: Int,

	@SerialName("preview")
	val preview: RedditFeedItemImageCollectionDto? = null,

	@SerialName("num_comments")
	val commentCount: Int,
)
