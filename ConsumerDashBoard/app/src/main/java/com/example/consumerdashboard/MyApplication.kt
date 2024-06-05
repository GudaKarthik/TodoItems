package com.example.consumerdashboard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        var config : RealmConfiguration = RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}