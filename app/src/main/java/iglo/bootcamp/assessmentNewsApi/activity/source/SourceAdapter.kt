package iglo.bootcamp.assessmentNewsApi.activity.source

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iglo.bootcamp.assessmentNewsApi.activity.list_article.ListArticleActivity
import iglo.bootcamp.assessmentNewsApi.databinding.SourceItemLayoutBinding
import iglo.bootcamp.common.entity.source.Source

class SourceAdapter(private val context: Context) : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    val data = AsyncListDiffer(this, differ)

    inner class SourceViewHolder(val binding: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder =
        SourceViewHolder(
            SourceItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.binding.data = (data.currentList[position])
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ListArticleActivity::class.java)
            intent.putExtra("EXTRA_SOURCE", data.currentList[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.currentList.size

    companion object {
        val differ = object : DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem == newItem
            }

        }
    }
}