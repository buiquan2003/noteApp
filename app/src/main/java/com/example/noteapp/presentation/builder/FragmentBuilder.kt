package com.example.noteapp.presentation.builder

import com.example.noteapp.presentation.ui.fragment.addNote.AddFragment
import com.example.noteapp.presentation.ui.fragment.calender.CalenderFragment
import com.example.noteapp.presentation.ui.fragment.detailNote.DetailFragment
import com.example.noteapp.presentation.ui.fragment.home.HomeFragment
import com.example.noteapp.presentation.ui.fragment.login.LogInFragment
import com.example.noteapp.presentation.ui.fragment.user.UserFragment
import com.example.noteapp.presentation.ui.fragment.search.SearchFragment
import com.example.noteapp.presentation.ui.fragment.signUp.SignUpFragment
import com.example.noteapp.presentation.ui.fragment.splash.Splash
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun splashFragment(): Splash
    @ContributesAndroidInjector
    abstract fun loginFragment(): LogInFragment

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment


    @ContributesAndroidInjector
    abstract fun signUpFragment(): SignUpFragment

    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun calenderFragment(): CalenderFragment

    @ContributesAndroidInjector
    abstract fun userFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun addFragment(): AddFragment

    @ContributesAndroidInjector
    abstract fun detailFragment(): DetailFragment



}