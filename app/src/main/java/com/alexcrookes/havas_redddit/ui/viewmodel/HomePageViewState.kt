package com.alexcrookes.havas_redddit.ui.viewmodel

import com.alexcrookes.havas_redddit.entities.DetailItem
import com.alexcrookes.havas_redddit.entities.FeedItem

sealed class HomePageViewState {
	data object Loading : HomePageViewState()
	data class FeedSuccess(val feedItems: List<FeedItem>) : HomePageViewState()
	data class StoryView(val story: DetailItem) : HomePageViewState()
	data class Error(val throwable: Throwable, val customMessage: String? = null) : HomePageViewState()
}
