package com.alexcrookes.havas_redddit.repository

import com.alexcrookes.havas_redddit.entities.DetailItem
import com.alexcrookes.havas_redddit.entities.FeedItem

interface RedditRepository {
	suspend fun getStoryDetails(id: String): Result<DetailItem>

	suspend fun getHomePage(): Result<List<FeedItem>>
}
