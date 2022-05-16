package com.example.notesapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ListItemBinding
import java.util.ArrayList

class RecyclerAdapter(
    private var notes: ArrayList<Notes>,
    private val onItemClickListener: OnItemClickListener,
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = notes[position]
        holder.bind(name)

        holder.itemBinding.tvDelete.setOnClickListener {
            onItemClickListener.delete(notes[position])
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.update(notes[position])
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateList(newList: List<Notes>){
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(var itemBinding: ListItemBinding, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(notes: Notes) {
            itemBinding.tvTitle.text = notes.title
            itemBinding.tvDescription.text = notes.description
            itemBinding.tvTimeStamp.text = "last updated: ${notes.timeStamp}"

        }


    }

    interface OnItemClickListener {

        fun update(notes: Notes)
        fun delete(notes: Notes)

    }



}
