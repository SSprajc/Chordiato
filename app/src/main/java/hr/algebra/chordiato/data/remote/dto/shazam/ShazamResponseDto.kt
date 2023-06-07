package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class ShazamResponseDto(
    @SerializedName("matches") val matches : List<MatchDto>?,
    @SerializedName("timestamp") val timestamp : String?,
    @SerializedName("timezone") val timezone : String?,
    @SerializedName("tagid") val tagid : String?,
    @SerializedName("track") val track : TrackDto?
)