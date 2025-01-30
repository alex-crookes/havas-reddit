package com.alexcrookes.havas_redddit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcrookes.havas_redddit.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
	private val repository: RedditRepository
) : ViewModel() {

	private val _viewState = MutableStateFlow<HomePageViewState>(HomePageViewState.Loading)
	val viewState = _viewState.asStateFlow()

	fun setIntent(intent: HomePageIntent) {
		when (intent) {
			is HomePageIntent.LoadHomePage ->
				loadHomePage()

			is HomePageIntent.LoadDetailStory ->
				loadDetailStory(intent.id)

			is HomePageIntent.CloseStory ->
				closeStory()
		}
	}

	// region HELPERS

	private fun loadHomePage() {
		viewModelScope.launch {
			_viewState.emit(HomePageViewState.Loading)
			repository.getHomePage().onSuccess { feed ->
				_viewState.emit(HomePageViewState.FeedSuccess(feed))
			}.onFailure {
				_viewState.emit(HomePageViewState.Error(it, "Home Page Cannot Be Loaded"))
			}
		}
	}

	private fun loadDetailStory(id: String) {
		viewModelScope.launch {
			_viewState.emit(HomePageViewState.Loading)

			repository.getStoryDetails(id)
				.onSuccess {
					_viewState.emit(HomePageViewState.StoryView(it))
				}
				.onFailure {
					_viewState.emit(HomePageViewState.Error(it, "Story Cannot Be Loaded"))
					// loadHomePage()...?
				}
		}
	}

	private fun closeStory() {
		loadHomePage()
	}

	// endregion
}
