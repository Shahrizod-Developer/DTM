package uz.gita.dtm.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dtm.data.models.tests.Question
import uz.gita.dtm.databinding.ItemQuestionBinding


class QuestionAdapter() :
    androidx.recyclerview.widget.ListAdapter<Question, QuestionAdapter.Holder>(ContactComparator) {

    private var itemOnClick: ((Question) -> Unit)? = null
    private lateinit var buttons: ArrayList<RadioButton>
    private lateinit var answer: String
    var score = 0

    fun onCLickItem(block: (Question) -> Unit) {
        itemOnClick = block
    }

    inner class Holder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                itemOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("CheckResult")
        fun bind() {

            val item = getItem(absoluteAdapterPosition)

            binding.question.text = item.question
            binding.number.text = (absoluteAdapterPosition + 1).toString()
            binding.ans1.text = item.ans1
            binding.ans2.text = item.ans2
            binding.ans3.text = item.ans3
            binding.ans4.text = item.ans4

            answer = ""
            binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    0 -> {
                        answer = binding.ans1.text.toString()
                    }
                    1 -> {
                        answer = binding.ans2.text.toString()
                    }
                    2 -> {
                        answer = binding.ans3.text.toString()
                    }
                    3 -> {
                        answer = binding.ans4.text.toString()
                    }
                }
            }
            if (answer == item.trueAns) {
                score += 3
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    object ContactComparator : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
                    && oldItem.ans1 == newItem.ans1
                    && oldItem.ans2 == newItem.ans2
                    && oldItem.ans3 == newItem.ans3
                    && oldItem.ans4 == newItem.ans4
                    && oldItem.trueAns == newItem.trueAns
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}