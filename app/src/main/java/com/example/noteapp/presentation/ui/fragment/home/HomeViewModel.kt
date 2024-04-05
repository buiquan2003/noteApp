package com.example.noteapp.presentation.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(

): ViewModel() {

    val float = MutableStateFlow(false)
    val recyclerView = MutableStateFlow(false)

    fun onClickOpenDetailFlightFragment(note: Note) = viewModelScope.launch {
      //  openDetailEvenChannel.send(OpenDetailEvent.NavigateToDetailFlightFragment(note))
    }

}