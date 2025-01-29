package com.alexcrookes.havas_redddit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.alexcrookes.havas_redddit.provider.RedditApiProvider
import com.alexcrookes.havas_redddit.provider.redditapi.RedditApiProviderImplementation
import com.alexcrookes.havas_redddit.repository.RedditRepository
import com.alexcrookes.havas_redddit.repository.RedditRepositoryImplementation
import com.alexcrookes.havas_redddit.ui.theme.HavasreddditTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		lifecycleScope.launch {
			val api: RedditApiProvider = RedditApiProviderImplementation()
			val repo: RedditRepository = RedditRepositoryImplementation(api)
			repo.getHomePage()
				.onSuccess {feedList ->
					Log.e("VM", "Loaded ${feedList.size} items")
					val item = feedList.firstOrNull()

					requireNotNull(item) { return@launch }
					Log.e("VM", "Get the story - ${item.itemName}")
					repo.getStoryDetails(item.itemName)
						.onSuccess {
							Log.e("VM", "Loaded story ${it.title}")
						}
						.onFailure {
							Log.e("VM", "Failed to load Story because ${it.message}")
						}

				}
				.onFailure {
					Log.e("VM", "Failed to load because ${it.message}")
				}


		}
		enableEdgeToEdge()
		setContent {
			HavasreddditTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Greeting(
						name = "Android",
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	HavasreddditTheme {
		Greeting("Android")
	}
}
