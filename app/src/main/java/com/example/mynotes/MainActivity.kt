package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        rv.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updatelist(it)
            }

        })


    }

    override fun OnItemClicked(note: Note) {
        viewModel.deleteNode(note)
        Toast.makeText(this,"${note.text}Deleted",Toast.LENGTH_LONG).show()


    }

    fun submitdata(view: View) {
        val noteText=etinput.text.toString()
        if(noteText.isNotEmpty())
        {
            viewModel.insertNode(Note(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_LONG).show()
        }
    }
}