package com.pog.passionfruitOrangeGuava.dagger

import android.app.Application

class PogApp: Application() {
    val appComponent: PogAppComponent = DaggerPogAppComponent.create()
}