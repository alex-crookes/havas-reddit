package com.alexcrookes.havas_redddit.provider.redditapi

import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiTests {
	private lateinit var api: RedditApiProvider

	@Before
	fun tearUp() {
		api = RedditApiProviderImplementation()
	}

	@Test
	fun test_GetHomePage() = runTest{
		val result = api.getHomePage()
		Assert.assertNotNull(result)
		val feed: RedditFeedDto = result.getOrElse {
			throw AssertionError("Feed not retrieved : ${it.message}")
		}

		Assert.assertEquals(25, feed.feedItems.size)
	}

}
