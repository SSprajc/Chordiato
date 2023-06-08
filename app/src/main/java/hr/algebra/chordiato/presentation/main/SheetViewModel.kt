package hr.algebra.chordiato.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.chordiato.core.util.Resource
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.use_case.GetSheetUseCase
import hr.algebra.chordiato.domain.use_case.RecognizeSongUseCase
import hr.algebra.chordiato.domain.use_case.RegisterRecognitionUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SheetViewModel : ViewModel() {

    private val recognizeSongUseCase = RecognizeSongUseCase()
    private val getSheetUseCase = GetSheetUseCase()
    private val registerRecognitionUseCase = RegisterRecognitionUseCase()

    private val _state = MutableSharedFlow<SheetState>()
    val state = _state.asSharedFlow()

    fun onEvent(event: SheetEvent) {
        when (event) {
            is SheetEvent.Recognize -> {
                recognizeSongUseCase(event.encodedSample).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            val track = result.data
                            if (track?.name != null) {
                                googleSearch(result.data)
                            } else {
                                _state.emit(SheetState(error = "Error in recognizing song"))
                            }
                        }
                        is Resource.Error -> {
                            _state.emit(SheetState(error = "Error in recognizing song"))
                        }
                        is Resource.Loading -> {
                            _state.emit(SheetState(isLoading = true))
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun googleSearch(track: Track) {
        getSheetUseCase(track.name, track.artist).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val link = result.data
                    track.link = link
                    _state.emit(SheetState(sheetUrl = link!!))

                    viewModelScope.launch {
                        registerRecognitionUseCase(track)
                    }
                }
                is Resource.Error -> {
                    _state.emit(SheetState(error = "Error, check network connection"))
                }
                is Resource.Loading -> {
                    _state.emit(SheetState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

}