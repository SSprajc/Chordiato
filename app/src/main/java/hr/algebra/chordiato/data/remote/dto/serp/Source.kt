package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("security")
    val security: String?,
    @SerializedName("source_info_link")
    val sourceInfoLink: String?
)