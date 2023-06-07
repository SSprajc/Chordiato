package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Urlparams(
    @SerializedName("{tracktitle}") val tracktitle : String,
    @SerializedName("{trackartist}") val trackartist : String
)
