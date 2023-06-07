package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.shazam.Actions

data class Apple(
    @SerializedName("actions") val actions : List<Actions>?
)
