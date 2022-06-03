package iglo.bootcamp.assessmentNewsApi.activity.list_article

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import iglo.bootcamp.assessmentNewsApi.databinding.ListArticleItemStateLayoutBinding
import iglo.bootcamp.common.ext.setGone
import iglo.bootcamp.common.ext.setVisible

class ListArticlePagingStateAdapter(val context: Context,
                                    val retryCallback: () -> Unit) : LoadStateAdapter<ListArticlePagingStateAdapter.ListArticleStateViewHolder>() {

    inner class ListArticleStateViewHolder(
        private val binding: ListArticleItemStateLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.retryContent.setOnClickListener {
                retryCallback()
            }
            when (loadState) {
                is LoadState.Error -> {
                    binding.loadingContent.setGone()
                    binding.retryContent.setVisible()
                    Log.i("LoadStateObserver", "Adapter LoadState Error")
                }
                is LoadState.Loading -> {
                    binding.loadingContent.setVisible()
                    binding.retryContent.setGone()
                    Log.i("LoadStateObserver", "Adapter LoadState Loading")
                }
                is LoadState.NotLoading -> {
                    binding.loadingContent.setGone()
                    binding.retryContent.setGone()
                    Log.i("LoadStateObserver", "Adapter LoadState NotLoading")
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ListArticleStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ListArticleStateViewHolder {
        return ListArticleStateViewHolder(
            ListArticleItemStateLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }
}