package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Notes)

    @Update
    suspend fun update(note: Notes)

    @Delete
    suspend fun delete(note: Notes)


//    @Query("SELECT * FROM notesTable WHERE title LIKE :name")
//    fun search (name : String) :LiveData<List<Notes>>

    @Query("SELECT * FROM notesTable Order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>

}