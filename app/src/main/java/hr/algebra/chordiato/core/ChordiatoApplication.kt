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
/*
    private lateinit var trackDao: TrackDao

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        var context: Context = ChordiatoApplication.applicationContext()
        var db = TrackDatabase.getInstance( this)
        trackDao = db.trackDao
    }
    companion object {
        private var instance: ChordiatoApplication? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    fun getTrackDao() = trackDao
 */
}