package com.example.pertemuan14firebase.repository

import com.example.pertemuan14firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow


interface MahasiswaRepository {
    suspend fun getMahasiswa(): Flow<List<Mahasiswa>>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(nim:String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
    suspend fun getMahasiswaByNim(nim: String):Flow<List<Mahasiswa>>
}
