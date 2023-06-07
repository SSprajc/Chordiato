package hr.algebra.chordiato.data.util

import hr.algebra.chordiato.data.local.entity.TrackEntity
import hr.algebra.chordiato.data.remote.dto.shazam.ShazamResponseDto
import hr.algebra.chordiato.domain.model.Track
import java.time.LocalDate

//Mappers remote
fun ShazamResponseDto.toTrack(): Track? {
    return track?.let {
        Track(
            it.key,
            it.title,
            it.subtitle,
            null,
            null,
            null
        )
    }
}

//Mappers local
fun TrackEntity.toTrack(): Track {
    return Track(
        id = id,
        name = name,
        artist = artist,
        favourite = favourite,
        link = link,
        lastPlayed = LocalDate.ofEpochDay(lastPlayed!!)
    )
}

fun Track.toTrackEntity(): TrackEntity {
    return TrackEntity(
        id,
        name,
        artist,
        favourite,
        link,
        lastPlayed?.toEpochDay() ?: LocalDate.now().toEpochDay()
    )
}
