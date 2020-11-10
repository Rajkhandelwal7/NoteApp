package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context:Context, private val listner:INoteRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val allnotes=ArrayList<Note>()
    inner class NoteViewHolder(itemVIew:View):RecyclerView.ViewHolder(itemVIew)
    {
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemVIew.findViewById<ImageView>(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listner.OnItemClicked(allnotes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentnote=allnotes[position]
        holder.textView.text=currentnote.text

    }

    override fun getItemCount(): Int {
        return allnotes.size
    }
    fun updatelist(newList:List<Note>)
    {
        allnotes.clear()
        allnotes.addAll(newList)
        notifyDataSetChanged()
    }

}
interface INoteRVAdapter{
    fun OnItemClicked(note:Note)
}