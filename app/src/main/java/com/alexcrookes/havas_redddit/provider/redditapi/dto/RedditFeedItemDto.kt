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
@Serializable
data class RedditFeedItemDataDto(
	@SerialName("name")
	val itemName: String,

	@SerialName("created")
	val created: Long, // Epoch time Stamp

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

@Serializable
data class RedditFeedItemImageCollectionDto(
	@SerialName("images")
	val images: List<RedditFeedImageDto>,
)

@Serializable
data class RedditFeedImageDto(
	@SerialName("id")
	val id: String,

	@SerialName("source")
	val source: RedditImageDefinitionDto,

	@SerialName("resolutions")
	val resolutions: List<RedditImageDefinitionDto>,
)

@Serializable
data class RedditImageDefinitionDto(
	@SerialName("url")
	val url: String,

	@SerialName("height")
	val height: Int,

	@SerialName("width")
	val width: Int,
)
