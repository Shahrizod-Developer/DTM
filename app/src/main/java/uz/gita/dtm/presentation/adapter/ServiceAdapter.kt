package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.gita.dtm.R
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.databinding.ItemServiceBinding
import java.lang.Exception
import kotlin.math.abs


class ServiceAdapter(private val context: Context) :
    androidx.recyclerview.widget.ListAdapter<Service, ServiceAdapter.Holder>(ContactComparator) {

    private var itemOnClick: ((Service) -> Unit)? = null

    fun onCLickItem(block: (Service) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(val binding: ItemServiceBinding) :
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

    object ContactComparator : DiffUtil.ItemCallback<Service>() {
        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.title == newItem.title && oldItem.id == newItem.id && oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}