package hr.algebra.chordiato.domain.use_case

import hr.algebra.chordiato.core.ChordiatoApplication
import hr.algebra.chordiato.data.local.repository.TrackLocalRepositoryImpl
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.repository.TrackLocalRepository

class ToggleFavouriteUseCase() {

    private val repository: TrackLocalRepository = ChordiatoApplication.instance.localRepo

    suspend operator fun invoke(track: Track) {
        if (track.favourite == true) {
            track.favourite = false
            repository.updateTrack(track)
        } else {
            track.favourite = true
            repository.updateTrack(track)
        }
    }
}