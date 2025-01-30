package com.alexcrookes.havas_redddit

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexcrookes.havas_redddit.ui.compose.DetailItemView
import com.alexcrookes.havas_redddit.ui.compose.FeedRecyclerWrapper
import com.alexcrookes.havas_redddit.ui.theme.HavasreddditTheme
import com.alexcrookes.havas_redddit.ui.viewmodel.HomePageIntent
import com.alexcrookes.havas_redddit.ui.viewmodel.HomePageViewModel
import com.alexcrookes.havas_redddit.ui.viewmodel.HomePageViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HavasApp: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val homePageVM: HomePageViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		homePageVM.setIntent(HomePageIntent.LoadHomePage)

		enableEdgeToEdge()
		setContent {
			val viewState = homePageVM.viewState.collectAsState()

			HavasreddditTheme {
				Scaffold(
					modifier = Modifier.fillMaxSize().padding(16.dp)
				) { _ ->
					Column(modifier = Modifier.padding(horizontal = 16.dp)) {
						when (val state = viewState.value) {
							is HomePageViewState.Loading -> LinearProgressIndicator()

							is HomePageViewState.Error ->
								Text(text = "Error: ${state.customMessage}")

							is HomePageViewState.FeedSuccess ->
								FeedRecyclerWrapper(
									stories = state.feedItems,
									onStoryClick = { storyId ->
										homePageVM.setIntent(
											HomePageIntent.LoadDetailStory(storyId)
										)
									},
								)

							is HomePageViewState.StoryView ->
								DetailItemView(
									detailItem = state.story,
									onClose = { homePageVM.setIntent(HomePageIntent.CloseStory) }
								)
						}
					}
				}
			}
		}
	}
}
