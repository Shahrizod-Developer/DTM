package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.gita.dtm.R
import uz.gita.dtm.data.models.tests.NewsLetter
import uz.gita.dtm.databinding.ItemNewsLetterBinding
import java.lang.Exception


class NewsLetterAdapter() :
    ListAdapter<NewsLetter, NewsLetterAdapter.Holder>(ContactComparator) {

    private var itemOnClick: ((NewsLetter) -> Unit)? = null

    fun onCLickItem(block: (NewsLetter) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(val binding: ItemNewsLetterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                itemOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("CheckResult")
        fun bind() {
            binding.title.text = getItem(absoluteAdapterPosition).title

            Picasso
                .get()
                .load(getItem(absoluteAdapterPosition).image)
                .into(binding.image, object : Callback {
                    override fun onSuccess() {
                        binding.lottieLoading.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.lottieLoading.visibility = View.GONE
                        binding.image.setImageResource(R.drawable.bachelor_cap_svgrepo_com)
                    }
                })
        }
    }

    object ContactComparator : DiffUtil.ItemCallback<NewsLetter>() {
        override fun areItemsTheSame(oldItem: NewsLetter, newItem: NewsLetter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsLetter, newItem: NewsLetter): Boolean {
            return oldItem.title == newItem.title && oldItem.id == newItem.id && oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemNewsLetterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}