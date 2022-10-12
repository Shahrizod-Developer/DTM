package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.gita.dtm.R
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.databinding.ItemNewsBinding
import java.lang.Exception
import java.text.SimpleDateFormat


class NewsAdapter(private val context: Context) :
    ListAdapter<News, NewsAdapter.Holder>(ContactComparator) {
    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var date: String

    private var loadingListener: (() -> Boolean)? = null
    private var itemClickListListener: ((News) -> Unit)? = null

    fun triggerLoadingListener(block: () -> Boolean) {
        loadingListener = block
    }

    fun triggerItemClickListener(block: (News) -> Unit) {
        itemClickListListener = block
    }

    object ContactComparator : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.desc == newItem.desc && oldItem.id == newItem.id && oldItem.image == newItem.image
        }

    }

    inner class Holder(private val binding: ItemNewsBinding) : ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind() {
            dateFormat = SimpleDateFormat("dd-MM-yyyy")

            val item = getItem(absoluteAdapterPosition)
            date = dateFormat.format(item.date)
            binding.tvItemDate.text = date
            binding.tvItemDescription.text = item.desc

            Picasso
                .get()
                .load(getItem(absoluteAdapterPosition).image)
                .into(binding.ivItem, object : Callback {
                    override fun onSuccess() {
                        binding.lottieLoading.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.lottieLoading.visibility = View.GONE
                        binding.ivItem.setImageResource(R.drawable.bachelor_cap_svgrepo_com)
                    }
                })
        }

        init {
            itemView.setOnClickListener {
                itemClickListListener?.invoke(getItem(absoluteAdapterPosition))
            }
            if (loadingListener?.invoke()!!) {
                binding.lottieLoading.visibility = View.VISIBLE
            } else {
                binding.lottieLoading.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }
}