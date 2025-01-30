package com.alexcrookes.havas_redddit

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexcrookes.havas_redddit.entities.FeedItem
import com.alexcrookes.havas_redddit.ui.compose.DetailItemView
import com.alexcrookes.havas_redddit.ui.theme.HavasreddditTheme
import com.alexcrookes.havas_redddit.ui.viewmodel.HomePageIntent
import com.alexcrookes.havas_redddit.ui.viewmodel.HomePageViewModel
import com.alexcrookes.havas_redddit.ui.viewmodel.HomePageViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltAndroidApp
class HavasApp: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val homePageVM: HomePageViewModel by viewModels()



	private val stories = mutableListOf<FeedItem>()

	private fun updateViewState(newState: HomePageViewState) = when (newState) {
		is HomePageViewState.Loading ->
			Log.e("TAG", "Loading")

		is HomePageViewState.Error -> Log.e("TAG", "Error")

		is HomePageViewState.FeedSuccess -> {
			Log.e("TAG", "updateViewState: FeedSize = ${newState.feedItems.size}")
			stories.clear()
			stories.addAll(newState.feedItems)
		}

		is HomePageViewState.StoryView ->
			Log.e("TAG", "updateViewState: Story = ${newState.story.title}")
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		lifecycleScope.launch {
			lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
				homePageVM.viewState.collect { updateViewState(it) }
			}
		}

		homePageVM.setIntent(HomePageIntent.LoadHomePage)

		lifecycleScope.launch {
			lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
				Log.e("VM", "Delaying 5000")
				delay(5_000)
				val storyId = stories.firstOrNull()?.itemName ?: "-1"
				homePageVM.setIntent(HomePageIntent.LoadDetailStory(
					storyId
				))
				Log.e("VM", "Loaded Story: $storyId")
			}
		}

		enableEdgeToEdge()
		setContent {
			HavasreddditTheme {
				Scaffold(modifier = Modifier.fillMaxSize().padding(16.dp)) { innerPadding ->
					Column (modifier = Modifier.padding(horizontal = 16.dp)) {

						DetailItemView(onClose = { Log.e("VM", "Closing") })
					}
				}
			}
		}
	}
}
