package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.domain.model.Track

data class TrackDto(
    @SerializedName("layout") val layout : Int,
    @SerializedName("type") val type : String,
    @SerializedName("key") val key : Int,
    @SerializedName("title") val title : String,
    @SerializedName("subtitle") val subtitle : String,
    @SerializedName("images") val images : Images?,
    @SerializedName("share") val share : Share?,
    @SerializedName("hub") val hub : Hub?,
    @SerializedName("url") val url : String?,
    @SerializedName("artists") val artists : List<Artists>?,
    @SerializedName("isrc") val isrc : String?,
    @SerializedName("genres") val genres : Genres?,
    @SerializedName("urlparams") val urlparams : Urlparams?,
    @SerializedName("myshazam") val myshazam : Myshazam?,
    @SerializedName("albumadamid") val albumadamid : Int?,
    @SerializedName("sections") val sections : List<Sections>?
) {

}

