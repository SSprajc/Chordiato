package hr.algebra.chordiato.presentation.main

import hr.algebra.chordiato.domain.model.Track

sealed class SheetEvent {
    data class Recognize(val encodedSample: String) : SheetEvent()
    data class Load(val trackId: Int) : SheetEvent()
}
