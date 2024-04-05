package com.example.noteapp.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.noteapp.extensions.connectInternet.ConnectObserver
import com.example.noteapp.extensions.connectInternet.NetworkObserver
import com.example.noteapp.extensions.coroutine.launchWhenStarted
import com.example.noteapp.presentation.factory.ViewModelFactoryHolder
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

abstract class BaseFragment<VB: ViewBinding>: DaggerFragment(), ViewModelFactoryHolder {

    protected lateinit var binding: VB

    protected val views: VB get() = binding!!

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    override val viewModelFactory: ViewModelProvider.Factory
        get() = mFactory

    private val connectObserver = NetworkObserver()
    protected var isNetworkConnected = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        checkNetWork()
        return views.root
    }

    private fun checkNetWork() {
        launchWhenStarted {
            connectObserver.observer().collect() {
                isNetworkConnected = it == ConnectObserver.Status.Available
            }
        }
    }

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}