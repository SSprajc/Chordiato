package hr.algebra.chordiato.data.remote.dto.serp


import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.serp.MenuItem

data class SearchInformation(
    @SerializedName("menu_items")
    val menuItems: List<MenuItem?>?,
    @SerializedName("organic_results_state")
    val organicResultsState: String?,
    @SerializedName("query_displayed")
    val queryDisplayed: String?,
    @SerializedName("time_taken_displayed")
    val timeTakenDisplayed: Double?,
    @SerializedName("total_results")
    val totalResults: Int?
)