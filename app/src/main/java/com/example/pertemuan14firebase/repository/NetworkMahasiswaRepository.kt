package com.example.pertemuan14firebase.repository

import com.example.pertemuan14firebase.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow

class NetworkMahasiswaRepository
    (private val firestore: FirebaseFirestore
            ):MahasiswaRepository{
    override suspend fun getMahasiswa(): Flow<List<Mahasiswa>> {

        val  mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("", Query.Direction.ASCENDING)
            .addSnapshotListener() { value, error ->

                if (value != null){
                    val mhsList = value.documents.mapNotNull{
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    trySend(mhsList)
                }
            }
        awaitClose{
            mhsCollection.remove()
        }



    }

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMahasiswa(nim: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMahasiswaByNim(nim: String): Flow<List<Mahasiswa>> {
        TODO("Not yet implemented")
    }
}