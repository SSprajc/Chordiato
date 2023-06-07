package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class Inline(
    @SerializedName("link")
    val link: String?,
    @SerializedName("title")
    val title: String?
)