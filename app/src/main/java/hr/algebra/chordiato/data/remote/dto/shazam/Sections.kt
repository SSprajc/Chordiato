package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.shazam.Metadata
import hr.algebra.chordiato.data.remote.dto.shazam.Metapages

data class Sections(
    @SerializedName("type") val type : String?,
    @SerializedName("metapages") val metapages : List<Metapages>?,
    @SerializedName("tabname") val tabname : String?,
    @SerializedName("metadata") val metadata : List<Metadata>?
)