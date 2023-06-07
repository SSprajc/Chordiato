package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.shazam.Actions
import hr.algebra.chordiato.data.remote.dto.shazam.Beacondata

data class Options(
    @SerializedName("caption") val caption : String?,
    @SerializedName("actions") val actions : List<Actions>?,
    @SerializedName("beacondata") val beacondata : Beacondata?,
    @SerializedName("image") val image : String?,
    @SerializedName("type") val type : String?,
    @SerializedName("listcaption") val listcaption : String?,
    @SerializedName("overflowimage") val overflowimage : String?,
    @SerializedName("colouroverflowimage") val colouroverflowimage : Boolean?,
    @SerializedName("providername") val providername : String?
)
