package hr.algebra.chordiato.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Track(
    val id: Int,
    val name: String,
    val artist: String,
    var favourite: Boolean? = false,
    var link: String?,
    var lastPlayed: LocalDate?
)
