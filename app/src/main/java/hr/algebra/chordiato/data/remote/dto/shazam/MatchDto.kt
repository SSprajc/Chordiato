package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("id") val id : Int?,
    @SerializedName("offset") val offset : Double?,
    @SerializedName("channel") val channel : Int?,
    @SerializedName("timeskew") val timeskew : Double?,
    @SerializedName("frequencyskew") val frequencyskew : Double?
)
