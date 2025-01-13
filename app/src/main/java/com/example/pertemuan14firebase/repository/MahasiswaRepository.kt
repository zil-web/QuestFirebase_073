package com.example.pertemuan14firebase.repository

import com.example.pertemuan14firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow


interface MahasiswaRepository {
    suspend fun getMahasiswa(): Flow<List<Mahasiswa>>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)
    suspend fun getMahasiswaByNim(nim: String):Flow<Mahasiswa>
}
