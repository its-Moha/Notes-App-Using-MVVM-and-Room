package com.example.notesapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Update

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotesRepository(private val notesDao: NotesDao) {

    // Room executes all queries on a separate thread.
    // Observed livData will notify the observer when the data has changed.
    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    suspend fun insert(note: Notes) {
        notesDao.insert(note)
    }

    suspend fun update(note: Notes) {
        notesDao.update(note)
    }

    suspend fun delete(note: Notes){
        notesDao.delete(note)
    }

//    fun search(name : String): LiveData<List<Notes>>{
//
//        return notesDao.search(name)
//    }


}

