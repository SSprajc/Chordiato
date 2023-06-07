package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class OrganicResult(
    @SerializedName("about_page_link")
    val aboutPageLink: String?,
    @SerializedName("about_page_serpapi_link")
    val aboutPageSerpapiLink: String?,
    @SerializedName("about_this_result")
    val aboutThisResult: AboutThisResult?,
    @SerializedName("cached_page_link")
    val cachedPageLink: String?,
    @SerializedName("displayed_link")
    val displayedLink: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("sitelinks")
    val sitelinks: Sitelinks?,
    @SerializedName("snippet")
    val snippet: String?,
    @SerializedName("snippet_highlighted_words")
    val snippetHighlightedWords: List<String?>?,
    @SerializedName("title")
    val title: String?
)