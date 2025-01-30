package com.alexcrookes.havas_redddit.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcrookes.havas_redddit.R
import com.alexcrookes.havas_redddit.entities.Meta
import com.alexcrookes.havas_redddit.ui.theme.HavasreddditTheme

@Composable
fun MetaBar(meta: Meta, modifier: Modifier = Modifier) {
	HavasreddditTheme {
		Row(modifier = modifier) {
			AssistChip(
				onClick = {},
				modifier = Modifier.padding(end = 16.dp),
				label = { Text(text = "${meta.upVotes}") },
				enabled = false,
				leadingIcon = {
					Icon(
						Icons.Outlined.ThumbUp,
						contentDescription = null,
						modifier = Modifier.size(AssistChipDefaults.IconSize)
				)}
			)

			AssistChip(
				onClick = {},
				label = { Text(text = "${meta.commentCount}") },
				enabled = false,
				leadingIcon = {
					Icon(
						painter = painterResource(id = R.drawable.outline_comment_24),
						contentDescription = null,
						modifier = Modifier.size(AssistChipDefaults.IconSize)
					)}
			)
		}
	}
}

private val testMeta = Meta(
	upVotes = 120,
	downVotes = 2,
	commentCount = 12
)

@Composable
@Preview(showBackground = true)
fun PREVIEW_MetaBar() {
	MetaBar(meta = testMeta, modifier = Modifier)
}
