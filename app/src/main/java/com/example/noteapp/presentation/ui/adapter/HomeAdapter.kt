package com.example.noteapp.presentation.ui.adapter


import com.example.noteapp.R
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.ItemNoteBinding

class HomeAdapter: BaseListAdapter<Note, ItemNoteBinding> (
    layoutId = { R.layout.item_note } ,
    onBind = { binding, noteModel, _ ->
        binding.apply {
            itemTitle.text = noteModel.title
            itemTextNote.text = noteModel.content
            itemTextTime.text = "${noteModel.time}"
            itemDateTime.text = "${noteModel.date}"
        }
    }
)

