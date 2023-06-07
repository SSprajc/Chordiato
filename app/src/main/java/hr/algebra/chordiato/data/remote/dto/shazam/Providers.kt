package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.shazam.Actions
import hr.algebra.chordiato.data.remote.dto.shazam.Images

data class Providers(
    @SerializedName("caption") val caption : String?,
    @SerializedName("images") val images : Images?,
    @SerializedName("actions") val actions : List<Actions>?,
    @SerializedName("type") val type : String?
)