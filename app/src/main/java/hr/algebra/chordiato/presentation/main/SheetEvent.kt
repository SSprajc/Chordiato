package hr.algebra.chordiato.presentation.main

sealed class SheetEvent {
    data class Recognize(val encodedSample: String) : SheetEvent()
}
