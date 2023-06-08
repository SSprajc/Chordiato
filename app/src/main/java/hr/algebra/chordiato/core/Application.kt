package hr.algebra.chordiato.core

import hr.algebra.chordiato.data.local.TrackDatabase
import hr.algebra.chordiato.data.local.repository.TrackLocalRepositoryImpl
import hr.algebra.chordiato.domain.repository.TrackLocalRepository

class Application(val native: ChordiatoApplication) {

    val localRepo: TrackLocalRepository = TrackLocalRepositoryImpl(this)

    fun provideDatabase() = TrackDatabase.getInstance(native)

}