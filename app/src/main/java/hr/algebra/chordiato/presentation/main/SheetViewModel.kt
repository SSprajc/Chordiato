package hr.algebra.chordiato.presentation.main

import android.media.AudioFormat
import android.media.MediaRecorder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.chordiato.core.util.Resource
import hr.algebra.chordiato.data.remote.api.shazam.ShazamService
import hr.algebra.chordiato.domain.model.Track
import hr.algebra.chordiato.domain.use_case.GetSheetUseCase
import hr.algebra.chordiato.domain.use_case.GetSongUseCase
import hr.algebra.chordiato.domain.use_case.RecognizeSongUseCase
import hr.algebra.chordiato.domain.use_case.RegisterRecognitionUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDateTime

const val AUDIO_SOURCE = MediaRecorder.AudioSource.MIC
const val SAMPLE_RATE = 44100
const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT

class SheetViewModel() : ViewModel() {

    private val recognizeSongUseCase = RecognizeSongUseCase()
    private val getSheetUseCase = GetSheetUseCase()
    private val registerRecognitionUseCase = RegisterRecognitionUseCase()
    //private val getSongUseCase = GetSongUseCase()


    private val _state = MutableSharedFlow<SheetState>()
    val state = _state.asSharedFlow()

    fun onEvent(event: SheetEvent) {
        when(event) {
            is SheetEvent.Recognize -> {
                recognizeSongUseCase(event.encodedSample).onEach { result ->
                    when(result) {
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
                        is Resource.Loading  -> {
                            _state.emit(SheetState(isLoading = true))
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is SheetEvent.Load -> {
                val trackId = event.trackId
                viewModelScope.launch {
                    //val track = getSongUseCase(trackId)
                    /*
                    if (track != null) {
                        _state.emit(SheetState(sheetUrl = track.link!!))
                    } else {
                        _state.emit(SheetState(error = "Error in finding song"))
                    }
                     */
                }
            }
        }
    }

    private fun googleSearch(track: Track) {
/*
        track.link = "https://tabs.ultimate-guitar.com/tab/elvis-presley/cant-help-falling-in-love-chords-1086983"

        viewModelScope.launch {
            registerRecognitionUseCase(track)
        }
 */

        getSheetUseCase(track.name, track.artist).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    val link = result.data
                    //timestamp
                    track.link = link
                    _state.emit(SheetState(sheetUrl = link!!))

                    viewModelScope.launch {
                        registerRecognitionUseCase(track)
                    }
                }
                is Resource.Error -> {
                    _state.emit(SheetState(error = "Error, check network connection"))
                }
                is Resource.Loading  -> {
                    _state.emit(SheetState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
         
    }

}