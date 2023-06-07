package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("id") val id : Int?,
    @SerializedName("adamid") val adamid : Int?
)
