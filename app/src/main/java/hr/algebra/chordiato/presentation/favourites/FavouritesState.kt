package hr.algebra.chordiato.presentation.favourites

import hr.algebra.chordiato.domain.model.Track

data class FavouritesState(
    val isLoading: Boolean = false,
    val tracks: List<Track> = emptyList(),
    val error: String = ""
)
