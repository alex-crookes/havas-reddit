package com.alexcrookes.havas_redddit.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcrookes.havas_redddit.entities.DetailItem
import com.alexcrookes.havas_redddit.entities.Meta
import com.alexcrookes.havas_redddit.ui.theme.HavasreddditTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailItemView(
	detailItem: DetailItem,
	onClose: () -> Unit = {},
	modifier: Modifier = Modifier
) {
	HavasreddditTheme {
		Column () {
			Row(modifier = Modifier
				.align(Alignment.End)
				.padding(top = 8.dp)
			) {
				Icon(
					imageVector = Icons.Outlined.Close,
					contentDescription = "Close",
					modifier = Modifier.clickable { onClose() }
				)
			}

			Text(
				modifier = Modifier.padding(top = 16.dp),
				style = MaterialTheme.typography.titleLarge,
				text = detailItem.title
			)

			detailItem.mainImage?.let {image ->
				GlideImage(
					modifier = Modifier.padding(top = 16.dp),
					model = image,
					contentDescription = detailItem.title,
					contentScale = ContentScale.FillWidth,
				)
			}

			detailItem.body?.let {body ->
				require (body.isNotEmpty()) { return@let }

				Text(
					modifier = Modifier.padding(top = 16.dp),
					style = MaterialTheme.typography.bodyMedium,
					text= body
				)
			}

			MetaBar(meta = detailItem.meta, modifier = Modifier.padding(top = 16.dp))
		}

	}
}

private val bodyAndImage = DetailItem(
	title ="This is a title",
	body = "This is the body text",
	meta = Meta(upVotes = 10, downVotes = 60, commentCount = 100),
	itemName = "dummy_item_name",
	created = System.currentTimeMillis(),
	mainImage =  "https://cdn.pastemagazine.com/www/articles/nic%20cage%20matchstick%20men%20main.PNG"
)

@Preview(showBackground = true)
@Composable
fun PREVIEW_DetailItemView() {
	DetailItemView(detailItem = bodyAndImage)
}
