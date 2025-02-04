package com.alexcrookes.havas_redddit.provider.redditapi

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
