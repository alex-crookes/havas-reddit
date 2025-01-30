package com.alexcrookes.havas_redddit.repository

import com.alexcrookes.havas_redddit.provider.RedditApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RedditRepositoryModule {
	@Provides
	fun provideRedditRepository(api: RedditApiProvider): RedditRepository {
		return RedditRepositoryImplementation(api)
	}
}
