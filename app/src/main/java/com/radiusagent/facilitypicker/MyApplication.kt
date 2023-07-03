package com.radiusagent.facilitypicker

import android.app.Application
import io.realm.Realm

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Configure Realm
        Realm.init(this)

    }
}
