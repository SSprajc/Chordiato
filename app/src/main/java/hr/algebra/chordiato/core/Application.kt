package hr.algebra.chordiato.core

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import hr.algebra.chordiato.data.local.TrackDatabase
import hr.algebra.chordiato.data.local.repository.TrackLocalRepositoryImpl
import hr.algebra.chordiato.domain.repository.TrackLocalRepository

class Application(val native: ChordiatoApplication) {

    val localRepo: TrackLocalRepository = TrackLocalRepositoryImpl(this)

    fun provideDatabase() = TrackDatabase.getInstance(native)

}