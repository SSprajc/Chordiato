package hr.algebra.chordiato.data.remote.dto.serp

import com.google.gson.annotations.SerializedName

data class SerpResponseDto(
    @SerializedName("organic_results")
    val organicResults: List<OrganicResult>?,
    @SerializedName("pagination")
    val pagination: Pagination?,
    @SerializedName("related_searches")
    val relatedSearches: List<RelatedSearche>?,
    @SerializedName("search_information")
    val searchInformation: SearchInformation?,
    @SerializedName("search_metadata")
    val searchMetadata: SearchMetadata?,
    @SerializedName("search_parameters")
    val searchParameters: SearchParameters?,
    @SerializedName("serpapi_pagination")
    val serpapiPagination: SerpapiPagination?
)