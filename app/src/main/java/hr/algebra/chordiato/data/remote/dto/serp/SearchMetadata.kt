package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class SearchMetadata(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("google_url")
    val googleUrl: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("json_endpoint")
    val jsonEndpoint: String?,
    @SerializedName("processed_at")
    val processedAt: String?,
    @SerializedName("raw_html_file")
    val rawHtmlFile: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("total_time_taken")
    val totalTimeTaken: Double?
)