package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("title") val title : String?,
    @SerializedName("text") val text : String?
)
