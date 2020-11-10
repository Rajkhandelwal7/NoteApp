package com.example.mynotes

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Delete
import java.util.concurrent.Flow

class NoteRepository(private  val notedao:NoteDao) {
    val allNotes: LiveData<List<Note>> = notedao.getAllnote()
    suspend fun insert(note:Note)
    {
        notedao.insert(note)
    }
    @Delete
    suspend  fun delete(note: Note)
    {
        notedao.delete(note)
    }


    }

