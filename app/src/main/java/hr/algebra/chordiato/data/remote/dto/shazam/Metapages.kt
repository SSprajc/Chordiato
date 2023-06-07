package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Metapages(
    @SerializedName("image") val image: String?,
    @SerializedName("caption") val caption: String?,
)
