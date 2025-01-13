package com.example.pertemuan14firebase.model


data class Mahasiswa (
    val nim: String,
    val nama: String,
    val jenis_kelamin: String,
    val alamat: String,
    val kelas: String,
    val angkatan: String
){
    constructor():this("","","","","","")

}