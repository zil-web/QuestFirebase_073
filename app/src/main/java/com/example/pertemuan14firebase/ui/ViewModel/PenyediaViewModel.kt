package com.example.pertemuan14firebase.ui.ViewModel


import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pertemuan14firebase.MahasiswaApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeMhsViewModel(mhsApp().container.mahasiswaRepository) }
        initializer { InsertMhsViewModel(mhsApp().container.mahasiswaRepository) }
        initializer { DetailMhsViewModel(createSavedStateHandle(),mhsApp().container.mahasiswaRepository) }
        initializer { EditMhsViewModel(mhsApp().container.mahasiswaRepository) }
    }
}

fun CreationExtras.mhsApp(): MahasiswaApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApp)