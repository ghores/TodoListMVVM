package com.example.todoappmvvm.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.models.Priority
import com.example.todoappmvvm.data.models.ToDoData
import com.example.todoappmvvm.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    //Binding
    private lateinit var binding: RowLayoutBinding
    var dataList = emptyList<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //InitViews
        binding.apply {
            binding.titleTxt.text = dataList[position].title
            binding.descriptionTxt.text = dataList[position].description
        }
        val priority = dataList[position].priority
        when (priority) {
            Priority.HIGH -> binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
            Priority.MEDIUM ->binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.yellow))
            Priority.LOW ->binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        }
    }

    fun setData(toDoData: List<ToDoData>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }

    inner class MyViewHolder() : RecyclerView.ViewHolder(binding.root) {

    }
}