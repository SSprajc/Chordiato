package hr.algebra.chordiato.domain.use_case

import hr.algebra.chordiato.core.ChordiatoApplication
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.repository.TrackLocalRepository
import kotlinx.coroutines.flow.Flow

class GetSongsUseCase() {

    private val repository: TrackLocalRepository = ChordiatoApplication.instance.localRepo

    operator fun invoke(onlyFavourites: Boolean): Flow<List<Track>> {
        return if (onlyFavourites) {
            repository.getFavouriteTracks()
        } else {
            repository.getAllTracks()
        }
    }
}