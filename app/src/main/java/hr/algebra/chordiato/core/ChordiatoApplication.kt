package hr.algebra.chordiato.core

import android.content.Context
import hr.algebra.chordiato.data.local.TrackDao
import hr.algebra.chordiato.data.local.TrackDatabase

class ChordiatoApplication : BaseApplication() {

    /** Instance of application. */
    val application: Application
        get() = instance

    override fun onCreate() {
        super.onCreate()
        // Create instance of application.
        instance = Application(this)
    }

    companion object {
        /** Single instance of application. Available after native application is created. */
        lateinit var instance: Application
            private set
    }

}