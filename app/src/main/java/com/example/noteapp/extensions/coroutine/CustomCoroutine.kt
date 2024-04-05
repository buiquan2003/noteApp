package com.example.noteapp.extensions.coroutine

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.launchWhenStarted(block: suspend CoroutineScope.() -> Unit) = viewLifecycleOwner.lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED){block()}
}

fun Fragment.launchWhenResumed(block: suspend CoroutineScope.() -> Unit) = viewLifecycleOwner.lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.RESUMED){block()}
}

fun Fragment.launchWhenCreate(block: suspend CoroutineScope.() -> Unit) = viewLifecycleOwner.lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.CREATED) { block() }
}

fun Fragment.launchWhenDestroy(block: suspend CoroutineScope.() -> Unit) = viewLifecycleOwner.lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.DESTROYED){block()}
}
