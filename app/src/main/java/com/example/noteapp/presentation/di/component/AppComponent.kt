package com.example.noteapp.presentation.di.component

import android.app.Application
import com.example.noteapp.presentation.builder.ActivityBuilder
import com.example.noteapp.presentation.builder.FragmentBuilder
import com.example.noteapp.presentation.di.module.AdapterModule
import com.example.noteapp.presentation.di.module.DatabaseModule
import com.example.noteapp.presentation.di.module.FirebaseModule
import com.example.noteapp.presentation.di.module.NetworkModule
import com.example.noteapp.presentation.di.module.RepositoryModule
import com.example.noteapp.presentation.di.module.ViewModelModule
import com.training.traveling.presentation.di.annotation.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    RepositoryModule::class,
    DatabaseModule::class,
    ViewModelModule::class,
    FragmentBuilder::class,
    FirebaseModule::class,
    AdapterModule::class,
    NetworkModule::class
])
interface AppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun binApplication(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication?)
}