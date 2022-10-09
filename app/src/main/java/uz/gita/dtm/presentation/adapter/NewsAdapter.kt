package uz.gita.dtm.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.dtm.R
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.databinding.ItemNewsBinding


class NewsAdapter(private val context: Context) :
    ListAdapter<News, NewsAdapter.Holder>(ContactComparator) {

    object ContactComparator : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.desc == newItem.desc && oldItem.id == newItem.id && oldItem.image == newItem.image
        }

    }

    inner class Holder(private val binding: ItemNewsBinding) : ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)


            val url = "${item.image}?w=360"
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivItem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }
}