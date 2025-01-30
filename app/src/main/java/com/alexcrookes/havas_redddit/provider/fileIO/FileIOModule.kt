package com.alexcrookes.havas_redddit.provider.fileIO

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FileIOModule {
	@Provides
	fun provideFileIO(): FileIO = FileIOImplementation()
}
