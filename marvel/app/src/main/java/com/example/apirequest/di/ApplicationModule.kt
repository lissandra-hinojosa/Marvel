package com.example.apirequest.di

import androidx.room.Room
import com.example.apirequest.database.AppDatabase
import com.example.apirequest.interactors.GetUserInteractor
import com.example.apirequest.interactors.LoginInteractor
import com.example.apirequest.interactors.RegisterInteractor
import com.example.apirequest.interactors.UpdateUserInteractor
import com.example.apirequest.repositories.MarvelRepository
import com.example.apirequest.repositories.UserRepository
import com.example.apirequest.services.MarvelService
import com.example.apirequest.viewmodels.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {
    viewModel { CharactersListViewModel( marvelRepository = get()) }
    viewModel { CharacterInfoViewModel( marvelRepository = get()) }
    viewModel { RegisterViewModel(registerInteractor = get()) }
    viewModel { LoginViewModel(  context = androidContext(),loginInteractor = get(), getUserInteractor = get(), updateUserInteractor = get()) }
    viewModel { ValidateInputViewModel() }
}

val interactorModule = module {
    single { RegisterInteractor(userRepository = get()) }
    single { LoginInteractor(userRepository = get()) }
    single { GetUserInteractor(userRepository = get()) }
    single { UpdateUserInteractor(userRepository = get())}
}

val serviceModule = module {
    single { MarvelService(getRetrofit("http://gateway.marvel.com/")) }
}

val repositoryModule = module {
    single { MarvelRepository(marvelService = get()) }
    single { UserRepository(userEntityDao = get()) }
}

val databaseModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java,"MarvelDatabase").build() }
    single { get<AppDatabase>().userEntityDao() }
}

//As "Application Module" is one of the first called classes, we need to create retrofit before the injection
fun getRetrofit(baseUrl: String):Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}