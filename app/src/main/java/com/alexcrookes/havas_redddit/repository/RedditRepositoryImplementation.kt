package com.alexcrookes.havas_redddit.repository

import android.util.Log
import com.alexcrookes.havas_redddit.entities.DetailItem
import com.alexcrookes.havas_redddit.entities.FeedItem
import com.alexcrookes.havas_redddit.entities.asDetailItem
import com.alexcrookes.havas_redddit.entities.asFeedItem
import com.alexcrookes.havas_redddit.provider.RedditApiProvider
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedItemDataDto
import javax.inject.Inject

class RedditRepositoryImplementation @Inject constructor(
	private val api: RedditApiProvider
): RedditRepository {

	// region Properties

	private val maxAge = 60_000L // 60 seconds
	private var feedItems: Map<String, RedditFeedItemDataDto> = emptyMap()
	private var lastLoad = 0L
	private val loadRequired: Boolean
		get() = feedItems.isEmpty() || (System.currentTimeMillis() > (lastLoad + maxAge))

	private val feedItemList: List<FeedItem>
		get() = feedItems.map { it.value.asFeedItem }
	// endregion

	// region IMPLEMENTATION (RedditRepository)

	override suspend fun getHomePage(): Result<List<FeedItem>> {
		require (loadRequired) {
			return Result.success(feedItemList)
		}

		val result = api.getHomePage().getOrElse {
			return Result.failure( Exception("Sorry"))
		}

		feedItems = result.feedItems.associate { it.data.itemName to it.data }
		lastLoad = System.currentTimeMillis()
		return Result.success(feedItemList)
	}

	override suspend fun getStoryDetails(id: String): Result<DetailItem> {
		if (feedItems.isEmpty()) { getHomePage() }

		val story = feedItems[id] ?: return Result.failure( Exception("Sorry"))

		return Result.success(story.asDetailItem)
	}

	// endregion

}
