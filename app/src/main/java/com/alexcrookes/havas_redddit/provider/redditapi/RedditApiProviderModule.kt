package com.alexcrookes.havas_redddit.provider.redditapi

import com.alexcrookes.havas_redddit.provider.RedditApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RedditApiProviderModule {

	@Provides
	fun provideRedditApi(): RedditApiProvider = RedditApiProviderImplementation()
}
