package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.gita.dtm.R
import uz.gita.dtm.data.models.persondata.Admission
import uz.gita.dtm.databinding.ItemAdmissionBinding
import java.lang.Exception


class AdmissionAdapter() :
    androidx.recyclerview.widget.ListAdapter<Admission, AdmissionAdapter.Holder>(ContactComparator) {

    private var itemOnClick: ((Admission) -> Unit)? = null

    fun onCLickItem(block: (Admission) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(val binding: ItemAdmissionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                itemOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("CheckResult")
        fun bind() {
            binding.name.text = getItem(absoluteAdapterPosition).name
            if (getItem(absoluteAdapterPosition).state == 1) {
                binding.confirmed.visibility = View.VISIBLE
            } else {
                binding.confirmed.visibility = View.GONE
            }
        }
    }

    object ContactComparator : DiffUtil.ItemCallback<Admission>() {
        override fun areItemsTheSame(oldItem: Admission, newItem: Admission): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Admission, newItem: Admission): Boolean {
            return oldItem.name == newItem.name && oldItem.state == newItem.state
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemAdmissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}