package hr.algebra.chordiato.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.algebra.chordiato.data.util.JsonParser
import java.time.LocalDateTime
/*
//@ProvidedTypeConverter
class Converters() {
    //@TypeConverter
    fun fromDateTimesJson(json: String) : List<LocalDateTime> {
        return Gson().fromJson<ArrayList<LocalDateTime>>(
            json,
            object : TypeToken<ArrayList<LocalDateTime>>(){}.type
        ) ?: emptyList()
    }
    //@TypeConverter
    fun toDateTimesJson(dateTimes: List<LocalDateTime>) : String {
        return Gson().toJson(
            dateTimes,
            object : TypeToken<ArrayList<LocalDateTime>>(){}.type
        ) ?: "[]" //empty list in json format
    }
}
 */