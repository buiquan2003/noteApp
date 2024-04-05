package com.example.noteapp.presentation.ui.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.noteapp.NoteActivity
import com.example.noteapp.R
import com.example.noteapp.data.model.ErrorType
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.extensions.connectInternet.ConnectObserver
import com.example.noteapp.extensions.connectInternet.NetworkObserver
import com.example.noteapp.extensions.injectVM
import com.example.noteapp.presentation.factory.ViewModelFactoryHolder
import com.example.noteapp.utils.Dialog.showCustomDialog
import com.example.noteapp.utils.Dialog.showDiaLog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ViewModelFactoryHolder {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    override val viewModelFactory: ViewModelProvider.Factory
        get() = mFactory

    private val viewModel: MainViewModel by injectVM()

    private lateinit var binding: ActivityMainBinding
    private val connectObserver = NetworkObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setUpBottomNavigation()
        showBottomNavigationView()
    }


    private fun setUpObserver() {
        lifecycleScope.launch {
            connectObserver.observer().collect {status ->
                when(status) {
                    ConnectObserver.Status.Available -> {
                        viewModel.apply {
                            getDataNote()
                        }
                    }

                    else -> {

                    }
                }
            }
        }
        if(!connectObserver.isNetworkConnect()){

        }
    }

    private fun showBottomNavigationView() {
        lifecycleScope.launch {
            viewModel.showBottomBar.collect { isShow ->
                binding.bottomNav.isVisible = isShow
            }
        }
    }

    private fun setUpBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNav.setupWithNavController(navController)
    }

}