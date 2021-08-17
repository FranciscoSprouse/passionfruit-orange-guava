package com.pog.passionfruitOrangeGuava.dagger

import com.pog.passionfruitOrangeGuava.features.search.ui.SearchFragment
import com.pog.passionfruitOrangeGuava.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface PogAppComponent {
    fun inject(app: PogApp)
    fun inject(activity: MainActivity)
    fun inject(fragment: SearchFragment)
}