package hr.algebra.chordiato.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName
import hr.algebra.chordiato.data.remote.dto.shazam.Apple

data class Myshazam(
    @SerializedName("apple") val apple : Apple?
)
