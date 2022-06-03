package iglo.bootcamp.assessmentNewsApi.activity.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iglo.bootcamp.assessmentNewsApi.activity.source.SourceActivity
import iglo.bootcamp.assessmentNewsApi.databinding.CategoryItemLayoutBinding

class CategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val data = AsyncListDiffer(this, differ)

    inner class CategoryViewHolder(private val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.categoryName.text = category
            binding.root.setOnClickListener {
                val intent = Intent(context, SourceActivity::class.java)
                intent.putExtra("EXTRA_CATEGORY", category)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data.currentList[position])
    }

    override fun getItemCount(): Int = data.currentList.size

    fun submitData(list:List<String>){
        data.submitList(list)
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}