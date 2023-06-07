package hr.algebra.chordiato.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.use_case.GetSongUseCase
import hr.algebra.chordiato.domain.use_case.GetSongsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class FavouritesViewModel(

) : ViewModel() {
    private val getSongsUseCase = GetSongsUseCase()

    private val _state = MutableStateFlow<FavouritesState>(FavouritesState())
    val state = _state.asStateFlow()

    init {
        getFavouriteTracks()
    }

    private var fetchJob: Job? = null

    private fun getFavouriteTracks() {
        fetchJob?.cancel()
        fetchJob = getSongsUseCase(true).onEach { result ->
            _state.emit(FavouritesState(tracks = result))
        }.launchIn(viewModelScope)

    }

}