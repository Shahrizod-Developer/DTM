package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.gita.dtm.R
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.databinding.ItemServiceSecondBinding
import java.lang.Exception


class SearchServiceAdapter() :
    androidx.recyclerview.widget.ListAdapter<Service, SearchServiceAdapter.Holder>(ContactComparator) {

    private var itemOnClick: ((Service) -> Unit)? = null

    fun onCLickItem(block: (Service) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(private val binding: ItemServiceSecondBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                itemOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("CheckResult")
        fun bind() {
            binding.title.text = getItem(absoluteAdapterPosition).title
        }
    }

    object ContactComparator : DiffUtil.ItemCallback<Service>() {
        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.title == newItem.title && oldItem.id == newItem.id && oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemServiceSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}