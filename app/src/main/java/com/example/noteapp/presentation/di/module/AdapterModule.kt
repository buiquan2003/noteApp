package com.example.noteapp.presentation.di.module

import com.example.noteapp.presentation.ui.adapter.HomeAdapter
import com.training.traveling.presentation.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @ApplicationScope
    @Provides
    fun provideHomeAdapter(): HomeAdapter = HomeAdapter()
}