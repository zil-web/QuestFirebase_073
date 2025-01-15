package com.example.pertemuan14firebase.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan14firebase.model.Mahasiswa
import com.example.pertemuan14firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.Exception

sealed class HomeMhsUiState{
    data class Success( val mahasiswa: List<Mahasiswa>) : HomeMhsUiState()
    data class Error (val message: Throwable) : HomeMhsUiState()
    object  Loading: HomeMhsUiState()
}


class HomeMhsViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    var mhsUiState: HomeMhsUiState by mutableStateOf(HomeMhsUiState.Loading)
        private set


    init {
        getMahasiswa()
    }

    fun getMahasiswa() {
        viewModelScope.launch {
            mhs.getMahasiswa()
                .onStart { mhsUiState = HomeMhsUiState.Loading
                }
                .catch {
                    mhsUiState = HomeMhsUiState.Error(it)
                }
                .collect {
                    mhsUiState = if (it.isEmpty()) {
                        HomeMhsUiState.Error(Exception("Data Kosong"))
                    }
                    else{
                        HomeMhsUiState.Success(it)
                    }
                }
        }

    }

    fun deleteMahasiswa(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try {
                mhs.deleteMahasiswa(mahasiswa)

            } catch (e: Exception) {
                mhsUiState = HomeMhsUiState.Error(e)
            }
        }
    }
    fun updateMahasiswa(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try {
                mhs.updateMahasiswa(mahasiswa) // Call repository to update the student
                getMahasiswa() // Reload the updated list
            } catch (e: Exception) {
                mhsUiState = HomeMhsUiState.Error(e)
            }
        }
    }
}



