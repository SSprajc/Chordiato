package hr.algebra.chordiato.domain.use_case

import hr.algebra.chordiato.core.ChordiatoApplication
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.repository.TrackLocalRepository

class RegisterRecognitionUseCase() {

    private val repository: TrackLocalRepository = ChordiatoApplication.instance.localRepo

    suspend operator fun invoke(track: Track) {
        val isAdded = repository.insertTrack(track)
        if (isAdded == -1L) {
            track.lastPlayed = null
            repository.updateTrack(track)
        }
    }
}