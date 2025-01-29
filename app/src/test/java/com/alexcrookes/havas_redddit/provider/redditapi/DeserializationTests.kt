package com.alexcrookes.havas_redddit.provider.redditapi

import com.alexcrookes.havas_redddit.provider.fileIO.FileIO
import com.alexcrookes.havas_redddit.provider.fileIO.FileIOImplementation
import com.alexcrookes.havas_redddit.provider.safeJson
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedImageDto
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedItemDataDto
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedItemDto
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditFeedItemImageCollectionDto
import com.alexcrookes.havas_redddit.provider.redditapi.dto.RedditImageDefinitionDto
import com.alexcrookes.havas_redddit.provider.redditapi.response.RedditFeedResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DeserializationTests {
	private val imageSource = "/reddit_image.json"
	private val feedImagePreviewItemSource = "/reddit_feed_image_preview.json"
	private val feedImageCollectionSource = "/reddit_image_collection.json"
	private val feedItemData = "/reddit_feed_item_data.json"
	private val feedItem = "/reddit_feed_item_t3_1icfsp1.json"
	private val homePage = "/reddit_homepage_response.json"

	lateinit var fileIO: FileIO

	@Before
	fun tearUp() {
		fileIO = FileIOImplementation()
	}


	@Test
	fun test_ImageSourceIsReadable() {
		val sut = fileIO.readFileFromResources(imageSource)
		Assert.assertNotNull(sut)
	}

	@Test
	fun test_ImageSourceIsDeserializable() {
		val sut = fileIO.readClassFromResources(
			imageSource, RedditImageDefinitionDto.serializer(), safeJson
		)
		Assert.assertNotNull(sut)
	}

	@Test
	fun test_FeedPreviewImageItemIsDeserialized() {
		val sut = fileIO.readClassFromResources(
			feedImagePreviewItemSource, RedditFeedImageDto.serializer(), safeJson
		)
		Assert.assertNotNull(sut)
	}


	@Test
	fun test_ImageCollectionIsDeserializable() {
		val sut = fileIO.readClassFromResources(
			feedImageCollectionSource, RedditFeedItemImageCollectionDto.serializer(),
			safeJson
		)
		Assert.assertNotNull(sut)
	}

	@Test
	fun test_feedItemDataDeserializes() {
		val sut = fileIO.readClassFromResources(
			feedItemData, RedditFeedItemDataDto.serializer(), safeJson
		)
		Assert.assertNotNull(sut)

		Assert.assertEquals("Trump ends Income Tax - what now?", sut?.title)
	}

	@Test
	fun test_feedItemDeserializes() {
		val sut = fileIO.readClassFromResources(
			feedItem, RedditFeedItemDto.serializer(), safeJson
		)
		Assert.assertNotNull(sut)
		Assert.assertEquals("Trump ends Income Tax - what now?", sut?.data?.title)
	}

	@Test
	fun test_HomePageDeserialization() {
		val sut = fileIO.readClassFromResources(
			homePage, RedditFeedResponse.serializer(), safeJson
		)

		Assert.assertEquals("Listing", sut?.kind)
		Assert.assertNotNull(sut)
	}
//	@Test
//	fun test_ImageCollectionIsDeserializable() {
//		val file = fileIO.readClassFromResources(feedImageCollectionSource, ListSerializer(RedditFeedItemImageCollection.serializer()))
//		Assert.assertNotNull(file)
//	}

}
