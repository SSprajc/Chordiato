package hr.algebra.chordiato.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.use_case.GetSongsUseCase
import hr.algebra.chordiato.domain.use_case.ToggleFavouriteUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val getSongsUseCase = GetSongsUseCase()
    private val toggleFavouriteUseCase = ToggleFavouriteUseCase()

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.GetSongs -> {
                getTracks()
            }
            is HistoryEvent.ToggleFavourite -> {
                val track = event.track
                viewModelScope.launch {
                    toggleFavouriteUseCase(track)
                    getTracks()
                }
            }
        }
    }

    private var fetchJob: Job? = null

    fun getTracks() {
        fetchJob?.cancel()
        fetchJob = getSongsUseCase(false).onEach { result ->
            _state.emit(HistoryState(tracks = emptyList()))
            _state.emit(HistoryState(tracks = result))
        }.launchIn(viewModelScope)
    }

    data class HistoryState(
        val isLoading: Boolean = false,
        val tracks: List<Track> = emptyList(),
    )
}
