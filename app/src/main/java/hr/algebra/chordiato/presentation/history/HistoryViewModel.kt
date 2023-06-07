package hr.algebra.chordiato.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.use_case.GetSongsUseCase
import hr.algebra.chordiato.domain.use_case.ToggleFavouriteUseCase
import hr.algebra.chordiato.presentation.favourites.FavouritesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HistoryViewModel() : ViewModel() {

    private val getSongsUseCase = GetSongsUseCase()
    private val toggleFavouriteUseCase = ToggleFavouriteUseCase()

    private val _state = MutableSharedFlow<HistoryState>()
    val state = _state.asSharedFlow()

    private var fetchJob: Job? = null

    init {
        getTracks()
    }

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

    fun getTracks() {

        fetchJob?.cancel()
        fetchJob = getSongsUseCase(false).onEach { result ->

            _state.emit(HistoryState(tracks = result))
            //_state.value = _state.value.copy(tracks = result)

        }.launchIn(viewModelScope)
    }

    data class HistoryState(
        val isLoading: Boolean = false,
        val tracks: List<Track> = emptyList(),
        val error: String = "",
    )
}
