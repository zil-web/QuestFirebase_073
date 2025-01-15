package com.example.pertemuan14firebase.ui.ViewModel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan14firebase.model.Mahasiswa
import com.example.pertemuan14firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


sealed class DetailMhsUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailMhsUiState()
    object Error : DetailMhsUiState()
    object Loading : DetailMhsUiState()
}

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {

    private val nim: String = checkNotNull(savedStateHandle["nim"]) // Ambil nim dari savedState

    // Menggunakan StateFlow untuk detailMhsUiState
    private val _detailMhsUiState = MutableStateFlow<DetailMhsUiState>(DetailMhsUiState.Loading)
    val detailMhsUiState: StateFlow<DetailMhsUiState> = _detailMhsUiState

    init {
        getMhsbyNim()
    }

    fun getMhsbyNim() {
        viewModelScope.launch {
            _detailMhsUiState.value =
                DetailMhsUiState.Loading // Menampilkan loading sebelum data diambil

            mahasiswaRepository.getMahasiswaByNim(nim)
                .onStart {
                    // Menunggu data dari Firestore
                }
                .catch {
                    // Tangani kesalahan
                    _detailMhsUiState.value = DetailMhsUiState.Error
                }
                .collect { mahasiswa ->
                    _detailMhsUiState.value =
                        DetailMhsUiState.Success(mahasiswa) // Jika berhasil, update UI
                }
        }
    }
}