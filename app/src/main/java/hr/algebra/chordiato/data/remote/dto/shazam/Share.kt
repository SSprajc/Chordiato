package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Share(
    @SerializedName("subject") val subject : String,
    @SerializedName("text") val text : String,
    @SerializedName("href") val href : String,
    @SerializedName("image") val image : String,
    @SerializedName("twitter") val twitter : String,
    @SerializedName("html") val html : String,
    @SerializedName("avatar") val avatar : String,
    @SerializedName("snapchat") val snapchat : String
)
