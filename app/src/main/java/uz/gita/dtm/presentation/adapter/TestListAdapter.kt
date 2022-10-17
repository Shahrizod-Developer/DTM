package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dtm.data.models.tests.TestListItem
import uz.gita.dtm.databinding.ItemTestBinding
import java.text.SimpleDateFormat


class TestListAdapter() :
    ListAdapter<TestListItem, TestListAdapter.Holder>(ContactComparator) {

    private var itemOnClick: ((TestListItem) -> Unit)? = null
    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var date: String

    fun onCLickItem(block: (TestListItem) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(val binding: ItemTestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                itemOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("CheckResult")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            dateFormat = SimpleDateFormat("dd-MM-yyyy")
            binding.name.text = item.name
            binding.lang.text = item.lang
            binding.date.text = dateFormat.format(item.date)
        }
    }

    object ContactComparator : DiffUtil.ItemCallback<TestListItem>() {
        override fun areItemsTheSame(oldItem: TestListItem, newItem: TestListItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TestListItem, newItem: TestListItem): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.lang == newItem.lang
                    && oldItem.date == newItem.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}