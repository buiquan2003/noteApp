package com.example.noteapp.presentation.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.HomeFragmentBinding
import com.example.noteapp.extensions.BaseFragment
import com.example.noteapp.extensions.coroutine.launchWhenStarted
import com.example.noteapp.extensions.injectVM
import com.example.noteapp.presentation.ui.activity.MainViewModel
import com.example.noteapp.presentation.ui.adapter.HomeAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment: BaseFragment<HomeFragmentBinding>() {

    private val viewModel: MainViewModel by injectVM()
    private val homeViewModel: HomeViewModel by injectVM()

    @Inject
    lateinit var homeAdapter: HomeAdapter

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiIt()
        setUpAdapter()
        clickItem()
    }

    private fun setUpAdapter() {
        binding.recyclerViewHome.adapter = homeAdapter

        launchWhenStarted {
            viewModel.listNote.collect {
                Log.d("aaaa","message: $it")
                homeAdapter.setData(it)
            }
//            val fakeListOfNotes = mutableListOf<Note>()
//            for (i in 1..10) {
//                val id = "id_$i"
//                val title = "Title"
//                val content = "Content $i"
//                val time = "Time $i"
//                val date = "Date $i"
//                val note = Note(id, title, content, time, date)
//                fakeListOfNotes.add(note)
//            }
//            viewModel.listNote.collect { notes ->
//                Log.d("aaaa", "Received list of notes: $notes")
//                homeAdapter.setData(notes)
//            }
        }
    }

    private fun uiIt() {
        lifecycleScope.launch {
            homeViewModel.float.collect {
             binding.floatingHome.setOnClickListener {
                 findNavController().navigate(R.id.action_homeFragment_to_addFragment)}

            }
        }

        lifecycleScope.launch{
            homeViewModel.recyclerView.collect {
                binding.recyclerViewHome.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
            }
        }
    }

    private fun clickItem() {
        homeAdapter.onClickItem = {noteModel,_ ->
            homeViewModel.onClickOpenDetailFlightFragment(noteModel)
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): HomeFragmentBinding =
        HomeFragmentBinding.inflate(layoutInflater)
}