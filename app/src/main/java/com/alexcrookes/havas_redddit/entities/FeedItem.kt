package com.alexcrookes.havas_redddit.entities

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedItemDataDto

data class FeedItem(
	val title: String,
	val itemName: String,
	val created: Long,
	val thumbnail: String?,
	val meta: Meta
)

data class Meta(
	val upVotes: Int,
	val downVotes: Int,
	val commentCount: Int,
)

val RedditFeedItemDataDto.asFeedItem: FeedItem
	get() {
		return FeedItem(
			title = title,
			itemName = itemName,
			created = created.toLong(),
			thumbnail = preview?.images?.firstOrNull()?.resolutions?.firstOrNull()?.url,
			meta = Meta(
				upVotes = this.upVotes,
				downVotes = this.downVotes,
				commentCount = this.commentCount
			)
		)
	}
