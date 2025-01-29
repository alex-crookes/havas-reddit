package com.alexcrookes.havas_redddit.provider.redditapi

import com.alexcrookes.havas_redddit.provider.RedditApiProvider
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
	}

}
