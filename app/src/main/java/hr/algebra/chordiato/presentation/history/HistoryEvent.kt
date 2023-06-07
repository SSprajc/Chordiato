package hr.algebra.chordiato.presentation.history

import hr.algebra.chordiato.domain.model.Track

sealed class HistoryEvent {
    object GetSongs:  HistoryEvent()
    data class ToggleFavourite(val track: Track) : HistoryEvent()
}