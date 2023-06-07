package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class SearchParameters(
    @SerializedName("device")
    val device: String?,
    @SerializedName("engine")
    val engine: String?,
    @SerializedName("google_domain")
    val googleDomain: String?,
    @SerializedName("num")
    val num: String?,
    @SerializedName("q")
    val q: String?
)