package com.example.notesapp

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var notes: ArrayList<Notes>


    lateinit var myAdapter: RecyclerAdapter
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        notes = ArrayList()

        openDialog()


        myAdapter = RecyclerAdapter(notes, this)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.setHasFixedSize(true)
        getCharacter()



    }



    fun getCharacter() {

        //initialize viewModel
        notesViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        //get liveData observer
        notesViewModel.allNotes.observe(this, androidx.lifecycle.Observer { list ->
            list?.let {
                myAdapter.updateList(it)
            }
        })
    }


    private fun openDialog() {

        binding.fab.setOnClickListener {
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)

            val title = dialogLayout.findViewById<EditText>(R.id.title)
            val description = dialogLayout.findViewById<EditText>(R.id.description)


            val builder = AlertDialog.Builder(this)
                .setView(dialogLayout)
                .setTitle("Add New Note")
                .setMessage("What do you want to do next?")
                .setIcon(R.drawable.note)
                .setPositiveButton("Add") { _, _ ->

                    val sdf = SimpleDateFormat("dd MMM, YYYY - HH:mm")
                    var currentDate: String = sdf.format(Date())

                    val t = title.text.toString()
                    val d = description.text.toString()


                    if ((title.text.toString()).isNotEmpty() && (description.text.toString()).isNotEmpty()) {
                        notesViewModel.addNote(Notes(t, d, currentDate))
                        myAdapter.notifyDataSetChanged()

                        Toast.makeText(this, "You Added New Note", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Text is empty, Please insert a title and description",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                .setNegativeButton("cancel") { _, _ ->
                    Toast.makeText(this, "You Clicked Cancel", Toast.LENGTH_SHORT).show()
                }.create()
            builder.show()
        }


    }

    override fun update(notes: Notes) {

        val intent = Intent(this,Data::class.java)
        intent.putExtra("noteTitle", notes.title)
        intent.putExtra("noteDescription", notes.description)
        intent.putExtra("noteId", notes.id)
        startActivity(intent)

    }

    override fun delete(notes: Notes) {
        notesViewModel.deleteNote(notes)
        Toast.makeText(this,"${notes.title} Has Been Deleted",Toast.LENGTH_SHORT).show()
    }



}





