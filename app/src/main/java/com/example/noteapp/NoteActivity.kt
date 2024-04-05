package com.example.noteapp

import com.example.noteapp.presentation.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NoteActivity : DaggerApplication() {

    private val applicationInjector= DaggerAppComponent.builder().binApplication(this).build()


    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector





    companion object {
        var instance: NoteActivity? = null
    }
}