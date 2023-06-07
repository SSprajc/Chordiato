package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.serp.OtherPages

data class Pagination(
    @SerializedName("current")
    val current: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("other_pages")
    val otherPages: OtherPages?
)