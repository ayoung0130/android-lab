package com.android.my_application

import android.app.Application
import com.android.my_application.util.RoomUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        RoomUtil.createDb(this)
    }
}