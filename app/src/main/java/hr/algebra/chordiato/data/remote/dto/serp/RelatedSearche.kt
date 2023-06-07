package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class RelatedSearche(
    @SerializedName("link")
    val link: String?,
    @SerializedName("query")
    val query: String?
)