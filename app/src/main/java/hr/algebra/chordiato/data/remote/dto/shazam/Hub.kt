package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Hub(
    @SerializedName("type") val type : String?,
    @SerializedName("image") val image : String?,
    @SerializedName("actions") val actions : List<Actions>?,
    @SerializedName("options") val options : List<Options>?,
    @SerializedName("providers") val providers : List<Providers>?,
    @SerializedName("explicit") val explicit : Boolean?,
    @SerializedName("displayname") val displayname : String?
)
