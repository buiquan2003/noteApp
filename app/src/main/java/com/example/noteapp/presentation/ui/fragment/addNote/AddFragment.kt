package com.example.noteapp.presentation.ui.fragment.addNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.AddFragmentBinding
import com.example.noteapp.extensions.BaseFragment
import com.example.noteapp.extensions.injectVM
import com.example.noteapp.utils.DateAndTime
import kotlinx.coroutines.launch

class AddFragment: BaseFragment<AddFragmentBinding>() {

   private val viewModel: AddViewModel by injectVM()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertNote()
        iuIt()
    }

    private fun insertNote() {
       binding.apply {
           cardFragmentAdd.setOnClickListener {
               if (titleAddFragment.text.toString().isEmpty() || contentAddFragment.text.toString().isEmpty()) {
                   Toast.makeText(requireContext(), R.string.please_enter_title_note, Toast.LENGTH_SHORT).show()
                   return@setOnClickListener
               }
               if (isNetworkConnected) {
                   viewModel.insertNote(
                       Note(
                           id = "",
                           titleAddFragment.text.toString(),
                           contentAddFragment.text.toString(),
                           String.format("%02d:%02d", DateAndTime.getHour(),DateAndTime.getMinute()),
                           "${DateAndTime.getDay()}/${DateAndTime.dayOfMonth()}"
                       )
                   )
                   titleAddFragment.text?.clear()
                   contentAddFragment.text?.clear()
               }
           }
       }
    }

//    private fun AddFragmentBinding.extracted() {
//        viewModel.showTextInCorrect.observe(viewLifecycleOwner) { showText ->
//            if (showText) {
//                titleAddFragment.text?.clear()
//                contentAddFragment.text?.clear()
//            }
//        }
//    }

    private fun iuIt() {
        lifecycleScope.launch {
            viewModel.addBack.collect {
                binding.arrowFragmentAdd.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): AddFragmentBinding =
        AddFragmentBinding.inflate(layoutInflater)

}
