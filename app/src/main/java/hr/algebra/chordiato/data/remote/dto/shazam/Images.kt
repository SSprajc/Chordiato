package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("overflow") val overflow : String?,
    @SerializedName("default") val default : String?
)
