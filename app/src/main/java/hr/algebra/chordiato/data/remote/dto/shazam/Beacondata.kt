package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Beacondata(
    @SerializedName("type") val type : String?,
    @SerializedName("providername") val providername : String?
)
