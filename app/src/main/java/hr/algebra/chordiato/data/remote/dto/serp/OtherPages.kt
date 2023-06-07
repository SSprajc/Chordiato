package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class OtherPages(
    @SerializedName("2")
    val x2: String?,
    @SerializedName("3")
    val x3: String?,
    @SerializedName("4")
    val x4: String?,
    @SerializedName("5")
    val x5: String?
)