package com.alexcrookes.havas_redddit.ui.viewmodel

sealed class HomePageIntent {
	data object LoadHomePage : HomePageIntent()
	data class LoadDetailStory(val id: String) : HomePageIntent()
	data object CloseStory: HomePageIntent()
}
