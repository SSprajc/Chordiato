package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.serp.Inline

data class Sitelinks(
    @SerializedName("inline")
    val `inline`: List<Inline?>?
)