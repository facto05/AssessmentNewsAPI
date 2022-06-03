package iglo.bootcamp.assessmentNewsApi.activity.list_article

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iglo.bootcamp.assessmentNewsApi.activity.detail_article.DetailArticleActivity
import iglo.bootcamp.assessmentNewsApi.databinding.ListArticleItemLayoutBinding
import iglo.bootcamp.common.entity.article.Article
import iglo.bootcamp.common.ext.loadImage

class ListArticlePagingAdapter(val context: Context) :
    PagingDataAdapter<Article, ListArticlePagingAdapter.ListArticleViewHolder>(differ) {

    inner class ListArticleViewHolder(val binding: ListArticleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.tvTitle.text = article?.title
            binding.tvDescription.text = article?.description
            binding.imgArticle.loadImage(article?.urlToImage)
            binding.root.setOnClickListener {
                val articleUrl = article?.url
                val intent = Intent(context, DetailArticleActivity::class.java)
                intent.putExtra("EXTRA_LINK_ARTICLE", articleUrl)
                context.startActivity(intent)
            }
        }
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ListArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListArticleViewHolder {
        return ListArticleViewHolder(
            ListArticleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}