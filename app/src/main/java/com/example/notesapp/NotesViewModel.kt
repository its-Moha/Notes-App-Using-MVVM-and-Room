package com.example.notesapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Notes>>
    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(notes)
    }

    fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(notes)
    }

    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }

//    fun search(name : String) : LiveData<List<Notes>> {
//
//        return repository.search(name)
//
//    }

}

//class NotesViewModel(application: Application) : AndroidViewModel(application) {
//
//   val allNotes: LiveData<List<Notes>>
//
//   val repository:NotesRepository
//
//    init {
//        val dao = NotesDatabase.getDatabase(application).getNotesDao()
//        repository = NotesRepository(dao)
//        allNotes = repository.allNotes
//    }
//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//    fun addNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
//        repository.insert(notes)
//    }
//
//    fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
//        repository.update(notes)
//    }
//
//    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
//        repository.delete(notes)
//    }
//}