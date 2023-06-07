package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Actions(
    @SerializedName("name") val name : String?,
    @SerializedName("type") val type : String?,
    @SerializedName("uri") val uri : String?
)
