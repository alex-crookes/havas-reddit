package com.alexcrookes.havas_redddit.ui.compose

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexcrookes.havas_redddit.databinding.FeedItemBinding
import com.alexcrookes.havas_redddit.databinding.FeedRecyclerBinding
import com.alexcrookes.havas_redddit.entities.FeedItem
import com.alexcrookes.havas_redddit.ui.theme.HavasreddditTheme
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

@Composable
fun FeedRecyclerWrapper(
	stories: List<FeedItem>, onStoryClick: (id: String) -> Unit, modifier: Modifier = Modifier
) {
	val context = LocalContext.current
	HavasreddditTheme {
		AndroidViewBinding(FeedRecyclerBinding::inflate) {
			with (this.feedRecycler) {
				adapter = FeedRecyclerAdapter(context, stories, onStoryClick)
				layoutManager = LinearLayoutManager(context)
			}
		}
	}
}

private class FeedRecyclerAdapter(
	private val context: Context,
	private val stories: List<FeedItem>,
	private val onClick: (item: String) -> Unit
) : RecyclerView.Adapter<FeedRecyclerAdapter.FeedItemViewHolder>() {

	inner class FeedItemViewHolder(val binding: FeedItemBinding): RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
		val binding = FeedItemBinding.inflate(LayoutInflater.from(context), parent, false)
		return FeedItemViewHolder(binding)
	}

	override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
		with (stories[position]) {
			holder.binding.title.text = this.title
			holder.binding.meta.setContent { MetaBar(this.meta) }

			holder.binding.image.hydrateIfNotNull(
				propertyCheck = { if (this.thumbnail.isNullOrBlank()) null else this.thumbnail },
				hydrate = { imageUrl ->
					Glide.with(holder.binding.root)
						.load(imageUrl)
						.override(Target.SIZE_ORIGINAL)
						.into(holder.binding.image)
				}
			)

			holder.binding.root.setOnClickListener { onClick(this.itemName) }
		}
	}


	override fun getItemCount(): Int = stories.size
}

private inline fun<TData> View.hydrateIfNotNull(
	propertyCheck: () -> TData?,
	hydrate: (TData) -> Unit
) {
	val value: TData? = propertyCheck()
	val isVisible = value != null

	this.visibility = when (isVisible) {
		true -> View.VISIBLE
		false -> View.GONE
	}

	value?.let { hydrate(it) }
}
