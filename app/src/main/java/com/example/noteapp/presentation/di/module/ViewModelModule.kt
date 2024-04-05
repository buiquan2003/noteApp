package com.example.noteapp.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.presentation.di.annotation.ViewModelKey
import com.example.noteapp.presentation.factory.ViewModelFactory
import com.example.noteapp.presentation.ui.activity.MainViewModel
import com.example.noteapp.presentation.ui.fragment.addNote.AddViewModel
import com.example.noteapp.presentation.ui.fragment.detailNote.DetailViewModel
import com.example.noteapp.presentation.ui.fragment.home.HomeViewModel
import com.example.noteapp.presentation.ui.fragment.login.LoginViewModel
import com.example.noteapp.presentation.ui.fragment.signUp.SignUpViewModel
import com.example.noteapp.presentation.ui.fragment.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignViewModel(signUpViewModel: SignUpViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLogInViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel::class)
    abstract fun binAddViewModel(addViewModel: AddViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun binDetailViewModel(detailViewModel: DetailViewModel): ViewModel

}