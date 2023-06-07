package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class MenuItem(
    @SerializedName("link")
    val link: String?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("serpapi_link")
    val serpapiLink: String?,
    @SerializedName("title")
    val title: String?
)