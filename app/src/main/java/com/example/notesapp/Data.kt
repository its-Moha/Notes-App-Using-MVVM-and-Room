package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityDataBinding
import java.text.SimpleDateFormat
import java.util.*




class Data : AppCompatActivity() {
    private lateinit var binding: ActivityDataBinding
    lateinit var notesViewModel: NotesViewModel
    var noteID = -1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Edit Note"

        // initialize the viewModel

        notesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)


        // on below line we are getting data passed via an intent.
//        val noteType = intent.getStringExtra(INTENT_PARCELABLE)

        // on below line we are setting data to edit text.
        val noteTitle = intent.getStringExtra("noteTitle")
        val noteDescription = intent.getStringExtra("noteDescription")
        noteID = intent.getIntExtra("noteId", -1)
        binding.tit.setText(noteTitle)
        binding.desc.setText(noteDescription)

         // on below line we are adding
        // click listener to our save button.
        binding.button.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = binding.tit.text.toString()
            val noteDescription = binding.desc.text.toString()


            // on below line we are checking the type
            // and then saving or updating the data.
//            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Notes(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    notesViewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()

                    startActivity(Intent(applicationContext, MainActivity::class.java))

                }

        }
    }
}


//        // get the text from the EditText and put it into second activities edittext
//        var name = intent.getSerializableExtra(INTENT_PARCELABLE) as Notes
//
//        binding.tit.setText(name.title)
//
//        binding.desc.setText(name.description)
//
//
//        position = intent.getIntExtra(INTENT_PARCELABLE, 0)
//
//
//        val title: String = binding.tit.text.toString()
//        val description: String = binding.desc.text.toString()
//
//
//        binding.button.setOnClickListener {
//            val data = Intent()
//
//            data.putExtra(INTENT_PARCELABLE, title)
//            data.putExtra(INTENT_PARCELABLE, description)
//            data.putExtra(INTENT_PARCELABLE, position)
//            setResult(Activity.RESULT_OK, data)
//            finish()
//        }




