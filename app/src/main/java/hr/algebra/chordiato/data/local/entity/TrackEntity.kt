package hr.algebra.chordiato.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class TrackEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val artist: String,
    val favourite: Boolean? = false,
    val link: String? = null,
    val lastPlayed: Long?
)