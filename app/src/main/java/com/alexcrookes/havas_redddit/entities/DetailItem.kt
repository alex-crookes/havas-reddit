package com.alexcrookes.havas_redddit.entities

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedItemDataDto

data class DetailItem(
	val title: String,
	val itemName: String,
	val created: Long,
	val body: String?,
	val mainImage: String?,
	val meta: Meta
)

val RedditFeedItemDataDto.asDetailItem: DetailItem
	get() {
		return DetailItem(
			title = title,
			itemName = itemName,
			created = created.toLong(),
			body = body,
			mainImage = preview?.images?.firstOrNull()?.source?.url,
			meta = Meta(
				upVotes = this.upVotes,
				downVotes = this.downVotes,
				commentCount = this.commentCount
			)
		)
	}
