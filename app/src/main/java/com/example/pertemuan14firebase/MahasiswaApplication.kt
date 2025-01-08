package com.example.pertemuan14firebase

import android.app.Application
import com.example.pertemuan14firebase.di.AppContainer
import com.example.pertemuan14firebase.di.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container:
            AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}