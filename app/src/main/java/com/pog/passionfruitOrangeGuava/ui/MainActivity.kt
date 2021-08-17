package com.pog.passionfruitOrangeGuava.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pog.passionfruitOrangeGuava.R
import com.pog.passionfruitOrangeGuava.dagger.PogApp
import com.pog.passionfruitOrangeGuava.dagger.PogAppComponent

class MainActivity: AppCompatActivity() {
    lateinit var appComponent: PogAppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as PogApp)
            .appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}