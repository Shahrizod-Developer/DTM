package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.gita.dtm.R
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.databinding.ItemApplicationBinding
import java.lang.Exception
import java.text.SimpleDateFormat


class ApplicationAdapter() :
    androidx.recyclerview.widget.ListAdapter<Application, ApplicationAdapter.Holder>(
        ContactComparator
    ) {

    private var itemOnClick: ((Application) -> Unit)? = null
    private lateinit var dateFormat: SimpleDateFormat

    fun onCLickItem(block: (Application) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(val binding: ItemApplicationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                itemOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("CheckResult", "SetTextI18n", "SimpleDateFormat")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            dateFormat = SimpleDateFormat("dd-MM-yyyy")
            binding.title.text = item.title
            binding.number.text = item.number
            binding.date.text = dateFormat.format(item.date) + item.time

            if (item.state) {
                binding.state.setBackgroundColor(Color.GREEN)
                binding.state.text = "To'langan"
            } else {
                binding.state.setBackgroundColor(Color.RED)
                binding.state.text = "To'langanmagan"
            }
            Picasso
                .get()
                .load(getItem(absoluteAdapterPosition).image)
                .into(binding.image)
        }
    }

    object ContactComparator : DiffUtil.ItemCallback<Application>() {
        override fun areItemsTheSame(oldItem: Application, newItem: Application): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Application, newItem: Application): Boolean {
            return oldItem.title == newItem.title && oldItem.id == newItem.id && oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemApplicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}